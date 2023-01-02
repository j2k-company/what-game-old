package com.j2k.whatgame

import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.j2k.whatgame.input.InputSignal

abstract class WorldScreen : ScreenAdapter() {
    abstract val batch: SpriteBatch
    abstract val renderer: Renderer
    abstract val player: Player
    abstract fun scroll(inputSignal: InputSignal)
}
