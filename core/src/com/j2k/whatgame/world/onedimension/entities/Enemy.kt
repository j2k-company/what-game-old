package com.j2k.whatgame.world.onedimension.entities

import space.earlygrey.shapedrawer.ShapeDrawer

class Enemy(
    override var position: Float,
    override var length: Float,
    override var health: Float
) : LivingEntity() {
    var initialHealth = health

    override fun render(shapeDrawer: ShapeDrawer) {
        shapeDrawer.setColor(1f, 0f, 0f, health/initialHealth)
        super.render(shapeDrawer)
    }

    override fun update() {

    }
}