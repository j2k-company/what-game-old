package com.j2k.whatgame.world.twodimension.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.j2k.whatgame.Assets
import com.j2k.whatgame.Player

class TwoDPlayer(var position: Vector2 = Vector2()) : Player {
    val texture = Assets.twoDPlayer

    override fun render(batch: SpriteBatch) {
        batch.draw(texture, position.x, position.y)
    }

    override fun update() {
        TODO("Not yet implemented")
    }

    override fun moveUp() {
        position.y += 10
    }

    override fun moveDown() {
        position.y -= 10
    }

    override fun moveLeft() {
        position.x -= 10
    }

    override fun moveRight() {
        position.x += 10
    }
}