package com.j2k.whatgame.world.onedimension.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.j2k.whatgame.Entity
import space.earlygrey.shapedrawer.ShapeDrawer

abstract class OneDEntity : Entity {
    abstract var position: Float
    abstract var length: Float

    open fun render(shapeDrawer: ShapeDrawer) {
        shapeDrawer.line(position, 0f, position + length, 0f)
    }

    override fun render(batch: SpriteBatch) { }
}
