package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.j2k.whatgame.Entity
import com.j2k.whatgame.Renderer

class TwoDRenderer() : Renderer {
    override fun render(batch: SpriteBatch, entities: Array<Entity>) {

    }

    fun renderMap(polyBatch: PolygonSpriteBatch, chunks: List<TerrainChunk>) {
        polyBatch.begin()
        chunks.forEach { it.render(polyBatch) }
        polyBatch.end()
    }
}
