package com.j2k.whatgame

import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.g2d.SpriteBatch

abstract class WorldScreen : ScreenAdapter() {
    abstract val batch: SpriteBatch
    abstract val renderer: Renderer
    abstract val player: Player
}
