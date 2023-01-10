package com.j2k.whatgame.world.twodimension.entities

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType
import com.j2k.whatgame.Player
import com.j2k.whatgame.world.twodimension.PhysicsManager


class TwoDPlayer(
    val texture: TextureRegion,
    val width: Float = texture.regionWidth.toFloat(),
    val height: Float = texture.regionHeight.toFloat(),
    position: Vector2 = Vector2(),
) : Player {

    private val body: Body
    private val fixture: Fixture
    init {
        val bodyDef = BodyDef().apply {
            type = BodyType.DynamicBody
            position.set(
                position.x + width/2,
                position.y + height/2
            )
            fixedRotation = true
        }

        body = PhysicsManager.createBody(bodyDef)

        val box = PolygonShape()
        val fixtureDef = FixtureDef().apply {
            shape = box
            density = 1.0f
            friction = 1f
        }

        box.setAsBox(width / 2, height / 2)
        fixture = body.createFixture(fixtureDef)
        box.dispose()
    }

    var friction by fixture::friction
    var position: Vector2
        get() = body.position.sub(Vector2(width/2, height/2))
        set(value) {
            body.setTransform(value.add(width/2, height/2), 0f)
        }

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