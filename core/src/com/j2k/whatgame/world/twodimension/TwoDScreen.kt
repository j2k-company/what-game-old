package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.j2k.whatgame.Player
import com.j2k.whatgame.Renderer
import com.j2k.whatgame.WorldScreen
import com.j2k.whatgame.world.twodimension.entities.TwoDPlayer

class TwoDScreen(override val batch: SpriteBatch) : WorldScreen() {
    override val renderer: Renderer = TwoDRenderer()
    override val player: Player = TwoDPlayer()

    fun createWorld() {

    }
}