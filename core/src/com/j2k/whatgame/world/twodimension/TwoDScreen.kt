package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.graphics.Camera
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.j2k.whatgame.Entity
import com.j2k.whatgame.WorldScreen
import com.j2k.whatgame.world.twodimension.entities.TwoDPlayer
import kotlin.random.Random

class TwoDScreen(override val batch: SpriteBatch) : WorldScreen() {
    override val renderer: TwoDRenderer = TwoDRenderer()
    override val player: TwoDPlayer = TwoDPlayer()
    private val polyBatch = PolygonSpriteBatch()

    private val worldGenerator = WorldGenerator(2000, 0.60, Random.nextInt())
    private val chunks = ArrayList<TerrainChunk>()
    private val entities = emptyArray<Entity>()

    val camera: Camera = OrthographicCamera(80f * 16, 45f * 16)

    init {
        chunks.apply {
            add(worldGenerator.generateChunk(-1))
            add(worldGenerator.generateChunk(0))
            add(worldGenerator.generateChunk(1))
        }
        for (y in 0 until TerrainChunk.HEIGHT) {
            if (chunks[1].getBlockConfiguration(0, y) == 0) {
                player.position = Vector2(0f, y.toFloat() * Block.SIZE)
                break
            }
        }
        camera.position.set(camera.viewportWidth / 2, camera.viewportHeight / 2, 0f)
    }

    val viewport: Viewport = ExtendViewport(
        camera.viewportWidth, camera.viewportHeight, camera
    )

    override fun render(delta: Float) {
        var playerChunk =
            player.position.x.toInt() / (Block.SIZE * TerrainChunk.WIDTH)

        if (player.position.x.toInt() < 0) playerChunk--

        if (playerChunk != chunks[1].position) {
            updateChunks(playerChunk)
        }

        camera.position.x = player.position.x
        camera.position.y = player.position.y

        batch.projectionMatrix = camera.combined
        polyBatch.projectionMatrix = camera.combined

        viewport.apply()

        super.render(delta)
        renderer.renderMap(polyBatch, chunks)
        renderer.render(batch, entities)

        batch.begin()
        player.render(batch)
        batch.end()
    }

    private fun updateChunks(playerChunk: Int) {
        when (playerChunk) {
            chunks[1].position - 1 -> {
                chunks.removeAt(2)
                chunks.add(0, worldGenerator.generateChunk(playerChunk - 1))
            }
            chunks[1].position + 1 -> {
                chunks.removeAt(0)
                chunks.add(worldGenerator.generateChunk(playerChunk + 1))
            }
            else -> {
                chunks.apply {
                    clear()
                    add(worldGenerator.generateChunk(playerChunk - 1))
                    add(worldGenerator.generateChunk(playerChunk))
                    add(worldGenerator.generateChunk(playerChunk + 1))
                }
            }
        }
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        viewport.update(width, height)
    }
}
