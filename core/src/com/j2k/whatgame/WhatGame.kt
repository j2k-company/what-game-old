package com.j2k.whatgame

import com.badlogic.gdx.Application
import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.j2k.whatgame.input.InputSignal
import com.j2k.whatgame.input.inputmanager.AndroidInputManager
import com.j2k.whatgame.input.inputmanager.DesktopInputManager
import com.j2k.whatgame.input.inputmanager.InputManager
import com.j2k.whatgame.world.onedimension.OneDScreen

class WhatGame : Game() {
    private lateinit var batch: SpriteBatch
    private lateinit var gameScreen: WorldScreen

    private lateinit var inputManager: InputManager

    override fun create() {
        inputManager = when(Gdx.app.type) {
            Application.ApplicationType.Android -> AndroidInputManager()
            Application.ApplicationType.Desktop -> DesktopInputManager()
            else -> throw InvalidPlatformException(
                "the ${Gdx.app.type.name} platform is not supported"
            )
        }

        batch = SpriteBatch()
        gameScreen = OneDScreen(batch)

        Gdx.input.inputProcessor = inputManager
        setScreen(gameScreen)
    }

    private fun handleInput(signal: InputSignal) {
        when(signal) {
            InputSignal.PLAYER_RIGHT_MOVE -> { gameScreen.player.moveRight() }
            InputSignal.PLAYER_LEFT_MOVE -> { gameScreen.player.moveLeft() }
            InputSignal.PLAYER_UP_MOVE -> { gameScreen.player.moveUp() }
            InputSignal.PLAYER_DOWN_MOVE -> { gameScreen.player.moveDown() }
        }
    }

    override fun render() {
        inputManager.events.forEach {
            handleInput(it)
        }

        ScreenUtils.clear(0.7f, 0.7f, 0.7f, 1f)
        super.render()
    }

    override fun dispose() {
        batch.dispose()
        super.dispose()
    }
}
