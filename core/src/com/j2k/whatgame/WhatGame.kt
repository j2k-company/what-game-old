package com.j2k.whatgame

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.j2k.whatgame.exception.InvalidPlatformException
import com.j2k.whatgame.input.inputmanager.AndroidInputManager
import com.j2k.whatgame.input.inputmanager.DesktopInputManager
import com.j2k.whatgame.input.inputmanager.InputManager
import com.j2k.whatgame.world.twodimension.TwoDScreen

class WhatGame : Game() {
    private lateinit var batch: SpriteBatch
    private lateinit var world: WorldScreen

    private lateinit var inputManager: InputManager

    override fun create() {
        inputManager = when (Gdx.app.type) {
            Application.ApplicationType.Android -> AndroidInputManager()
            Application.ApplicationType.Desktop -> DesktopInputManager()
            else -> throw InvalidPlatformException(
                "the ${Gdx.app.type.name} platform is not supported"
            )
        }

        batch = SpriteBatch()
        world = TwoDScreen(batch)

        Gdx.input.inputProcessor = inputManager
        setScreen(world)
    }

    override fun render() {
        inputManager.events.forEach {
            world.handleInput(it)
        }
        inputManager.removeTemporaryEvents()

        ScreenUtils.clear(
            0x6A / 255f,
            0x84 / 255f,
            0xD2 / 255f,
            1f
        )
        super.render()
    }

    override fun dispose() {
        batch.dispose()
        super.dispose()
    }
}
