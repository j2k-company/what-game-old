package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.j2k.whatgame.Entity
import com.j2k.whatgame.Renderer

class TwoDRenderer() : Renderer {
    override fun render(batch: SpriteBatch, entities: Array<Entity>) {

    }

    fun renderMap(batch: SpriteBatch, chunks: List<TerrainChunk>) {
        batch.begin()
        chunks.forEach { it.render(batch) }
        batch.end()
    }
}
