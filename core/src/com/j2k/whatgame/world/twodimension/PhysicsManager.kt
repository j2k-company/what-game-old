package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.math.Matrix4
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.*

import com.j2k.whatgame.utils.BodyEditorLoader


object PhysicsManager {
    private const val STEP_TIME = 1f / 60f
    private const val VELOCITY_ITERATIONS = 6
    private const val POSITION_ITERATIONS = 2

    private var accumulator = 0f

    private val world  by lazy { World(Vector2(0f, -10f * Block.SIZE), true) }
    private val loader by lazy {
        BodyEditorLoader(Gdx.files.internal("block_conf.json"))
    }

    fun createBody(def: BodyDef): Body = world.createBody(def)

    fun createBlock(position: Vector2, conf: Int) {
        val bd = BodyDef()
        bd.position.set(position)
        val body = createBody(bd)
        val fd = FixtureDef()
        loader.attachFixture(body, conf.toString(), fd, 1f)
    }

    fun doPhysicalStep(delta: Float) {
        accumulator += delta.coerceAtMost(0.25f)

        if (accumulator >= STEP_TIME) {
            accumulator -= STEP_TIME

            world.step(STEP_TIME, VELOCITY_ITERATIONS, POSITION_ITERATIONS)
        }
    }

    fun createBlock(xPos: Float, yPos: Float, conf: Int) {
        createBlock(Vector2(xPos, yPos), conf)
    }

    private val debugRenderer by lazy { Box2DDebugRenderer() }

    fun renderDebug(projectionMatrix: Matrix4) {
        debugRenderer.render(world, projectionMatrix)
    }
}
