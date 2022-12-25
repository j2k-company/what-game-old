package com.j2k.whatgame.world.onedimension.entities

import space.earlygrey.shapedrawer.ShapeDrawer
import kotlin.properties.Delegates
import kotlin.random.Random

enum class Behaviour {
    WANDERING, ATTACK, FLIGHT
}

class Enemy(
    override var position: Float,
    override var length: Float,
    override var health: Float,
    val max_speed: Float,
    val habitat: Pair<Int, Int>
) : LivingEntity() {
    val initialHealth = health
    val habitat_length = habitat.second - habitat.first
    val behaviour = Behaviour.WANDERING

    private var speed: Float = 0f
        set(value) {
            field = if (value > max_speed) max_speed
            else if (value < -max_speed) -max_speed
            else value
        }

    private var targetPosition by Delegates.notNull<Int>()
    private var targetIsAhead by Delegates.notNull<Boolean>()
    private var desiredSpeed by Delegates.notNull<Float>()

    init {
        newTargetPosition()
    }

    private fun newTargetPosition() {
        targetPosition = Random.nextInt(habitat.first, habitat.second)
        targetIsAhead = targetPosition > position

        val distance = targetPosition - position
        desiredSpeed = (distance / habitat_length) * (max_speed / 2)
    }

    private fun wander() {
    }

    private fun seek(targetPosition: Int) {
        val distance = targetPosition - position
        var desiredSpeed = (distance / habitat_length) * max_speed
        if (desiredSpeed > 0 && desiredSpeed > max_speed) {
            desiredSpeed = max_speed
        } else if (desiredSpeed < 0 && desiredSpeed < -max_speed) {
            desiredSpeed = -max_speed
        }
        val steering = (desiredSpeed - speed) / length

        speed += steering
        }

    override fun render(shapeDrawer: ShapeDrawer) {
        shapeDrawer.setColor(1f, 1f, 0f, 1f)
        shapeDrawer.line(targetPosition.toFloat(), 0f, targetPosition + 5f, 0f)
        shapeDrawer.setColor(1f, 0f, 0f, health / initialHealth)
        super.render(shapeDrawer)
    }

    override fun update() {
//        if(targetIsAhead && speed < desiredSpeed
//            || !targetIsAhead && speed > desiredSpeed) {
//            speed += desiredSpeed
//        }
//
//        position += speed
//
//        if (
//            targetIsAhead && position > targetPosition
//            || !targetIsAhead && position < targetPosition
//        ) {
//            newTargetPosition()
//        }
        if (position < 199 && speed >= 0) seek(200)
        else seek(-100)
        position += speed

    }
}
