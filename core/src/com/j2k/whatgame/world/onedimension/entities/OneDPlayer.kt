package com.j2k.whatgame.world.onedimension.entities

import com.j2k.whatgame.Player
import space.earlygrey.shapedrawer.ShapeDrawer

class OneDPlayer(
    override var position: Float,
    override var length: Float,
    override var health: Float
) : LivingEntity(), Player {

    override fun render(shapeDrawer: ShapeDrawer) {
        shapeDrawer.setColor(0f, 1f, 0f, 1f)
        super.render(shapeDrawer)
    }

    override fun update() { }

    override fun moveLeft() {
        position -= 5
    }

    override fun moveRight() {
        position += 5
    }

    override fun moveUp() {}
    override fun moveDown() {}
}
