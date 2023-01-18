package com.j2k.whatgame

import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.j2k.whatgame.input.InputSignal

abstract class WorldScreen : ScreenAdapter() {
    protected abstract val batch: SpriteBatch
    protected abstract val renderer: Renderer
    abstract val player: Player

    open fun handleInput(signal: InputSignal) {
        when(signal) {
            InputSignal.PLAYER_RIGHT_MOVE -> { player.moveRight() }
            InputSignal.PLAYER_LEFT_MOVE -> { player.moveLeft() }
            InputSignal.PLAYER_UP_MOVE -> { player.moveUp() }
            InputSignal.PLAYER_DOWN_MOVE -> { player.moveDown() }
        }
    }
}
