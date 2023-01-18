package com.j2k.whatgame.world.onedimension

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.j2k.whatgame.Player
import com.j2k.whatgame.Renderer
import com.j2k.whatgame.WorldScreen


class OneDScreen(override val batch: SpriteBatch) : WorldScreen() {
    override val renderer: Renderer = OneDRenderer()
    override val player: Player = TODO("Not yet implemented")
}
