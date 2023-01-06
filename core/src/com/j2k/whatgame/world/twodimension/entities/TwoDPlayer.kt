package com.j2k.whatgame.world.twodimension.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.j2k.whatgame.Player

class TwoDPlayer(
    val texture: TextureRegion,
    val width: Float = texture.regionWidth.toFloat(),
    val height: Float = texture.regionHeight.toFloat(),
    var position: Vector2 = Vector2()
) : Player {

    override fun render(batch: SpriteBatch) {
        batch.draw(texture, position.x, position.y, width, height)
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