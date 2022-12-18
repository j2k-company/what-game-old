package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2

class TerrainChunk {
    // array with values 0..15
    private val configurations = Array(WIDTH) { IntArray(HEIGHT) { 0 } }
    // array with object ids in the Block enum
    private val blocks = Array(WIDTH) { IntArray(HEIGHT) { 0 } }

    fun render(polyBatch: PolygonSpriteBatch) {
        for (x in 0 until WIDTH) {
            for (y in 0 until HEIGHT) {
                val conf = configurations[x][y]
                val texture = Block.values()[blocks[x][y]].textures[conf]

                MarchingSquares.drawSquare(
                    conf, texture, Block.SIZE, polyBatch,
                    Vector2((x * Block.SIZE).toFloat(), (y * Block.SIZE).toFloat())
                )
            }
        }

    }

    companion object {
        // chunk size in blocks
        const val WIDTH = 500
        const val HEIGHT = 250
    }
}