package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.j2k.whatgame.Entity
import com.j2k.whatgame.Player
import com.j2k.whatgame.Renderer
import com.j2k.whatgame.WorldScreen
import com.j2k.whatgame.world.twodimension.entities.TwoDPlayer

class TwoDScreen(override val batch: SpriteBatch) : WorldScreen() {
    override val renderer: TwoDRenderer = TwoDRenderer(PolygonSpriteBatch())
    override val player: Player = TwoDPlayer()
    val entities = emptyArray<Entity>()

    val worldGenerator = WorldGenerator(255, 0.55, 354)
    val chunks = mutableMapOf<Int, TerrainChunk>()

    init {
        chunks[0] = worldGenerator.generateChunk(0)
    }

    override fun render(delta: Float) {
        super.render(delta)
        renderer.renderMap(chunks.values.toList())
        renderer.render(batch, entities)
    }
}