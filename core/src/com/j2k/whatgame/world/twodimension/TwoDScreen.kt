package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.j2k.whatgame.Entity
import com.j2k.whatgame.WorldScreen
import com.j2k.whatgame.input.InputSignal
import com.j2k.whatgame.world.twodimension.entities.TwoDPlayer
import kotlin.random.Random

private const val ZOOM_SPEED = 0.3f
private const val MAX_ZOOM = 2f
private const val MIN_ZOOM = 0.3f

class TwoDScreen(override val batch: SpriteBatch) : WorldScreen() {
    override val renderer: TwoDRenderer = TwoDRenderer()
    override val player: TwoDPlayer = TwoDPlayer(AssetManager.player,
        AssetManager.player.regionWidth/16f, AssetManager.player.regionHeight/16f)

    private val polyBatch = PolygonSpriteBatch()

    private val worldGenerator = WorldGenerator(2000, 0.60, Random.nextInt())
    private val chunks = ArrayList<TerrainChunk>().apply {
        add(worldGenerator.generateChunk(-1))
        add(worldGenerator.generateChunk(0))
        add(worldGenerator.generateChunk(1))
    }
    private val entities = emptyArray<Entity>()

    private val camera: OrthographicCamera = OrthographicCamera(
        80f * BLOCK_SIZE, 45f * BLOCK_SIZE
    )

    init {
        // FIXME:TODO: move to function for creating physical block bodies
        // initial blocks
        for (x in 0 until CHUNK_WIDTH) {
            for (y in 200 until CHUNK_HEIGHT) {
                PhysicsManager.createBlock(
                    chunks[1].run {
                        x * BLOCK_SIZE + position * BLOCK_SIZE * CHUNK_WIDTH
                    }.toFloat(), (y * BLOCK_SIZE).toFloat(),
                    chunks[1].getBlockConfiguration(x, y)
                )
            }
        }
        // init player position
        for (y in 0 until CHUNK_HEIGHT) {
            if (chunks[1].getBlockConfiguration(0, y) == 0) {
                player.position = Vector2(0f, y.toFloat() * BLOCK_SIZE)
                break
            }
        }
    }

    val viewport: Viewport = ExtendViewport(
        camera.viewportWidth, camera.viewportHeight, camera
    )

    override fun render(delta: Float) {
        var playerChunk =
            player.position.x.toInt() / (BLOCK_SIZE * CHUNK_WIDTH)

        if (player.position.x.toInt() < 0) playerChunk--

        if (playerChunk != chunks[1].position) {
            updateChunks(playerChunk)
        }

        // FIXME:BUG: the camera does not keep up with the character
        camera.position.x = player.position.x
        camera.position.y = player.position.y

        batch.projectionMatrix = camera.combined
        polyBatch.projectionMatrix = camera.combined

        viewport.apply()

        super.render(delta)
        renderer.renderMap(batch, chunks)
        renderer.render(batch, entities)

        batch.begin()
        player.render(batch)
        batch.end()

        PhysicsManager.doPhysicalStep(delta)
//        PhysicsManager.renderDebug(camera.combined)
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

    override fun handleInput(signal: InputSignal) {
        super.handleInput(signal)
        when (signal) {
            InputSignal.SCROLLED_UP -> { scroll(true) }
            InputSignal.SCROLLED_DOWN -> { scroll(false) }
        }
    }

    private fun scroll(isUp: Boolean) {
        if (isUp && camera.zoom > MIN_ZOOM) {
            camera.zoom -= ZOOM_SPEED
        } else if (!isUp && camera.zoom < MAX_ZOOM) {
            camera.zoom += ZOOM_SPEED
        }
    }

    override fun resize(width: Int, height: Int) {
        super.resize(width, height)
        viewport.update(width, height)
    }

    override fun dispose() {
        polyBatch.dispose()
        AssetManager.dispose()
    }
}
