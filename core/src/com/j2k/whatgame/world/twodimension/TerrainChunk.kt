package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.math.Vector2

class TerrainChunk(val position: Int) {
    // array with values 0..15
    private val configurations = Array(WIDTH) { IntArray(HEIGHT) { 0 } }
    // array with object ids in the Block enum
    private val blocks = Array(WIDTH) { IntArray(HEIGHT) { 0 } }

    fun render(polyBatch: PolygonSpriteBatch) {
        for (x in 0 until WIDTH) {
            for (y in 0 until HEIGHT) {
                val conf = configurations[x][y]
                val texture = Block.values()[blocks[x][y]].getTexture(conf)

                MarchingSquares.drawSquare(
                    conf, texture, Block.SIZE, polyBatch,
                    Vector2(
                        (x * Block.SIZE + position * Block.SIZE * WIDTH).toFloat(),
                        (y * Block.SIZE).toFloat()
                    )
                )
            }
        }
    }

    fun setBlock(blockId: Int, configuration: Int, x: Int, y: Int) {
        blocks[x][y] = blockId
        configurations[x][y] = configuration
    }

    fun getBlock(x: Int, y: Int): Block {
        return Block.values()[blocks[x][y]]
    }

    fun getBlockConfiguration(x: Int, y: Int): Int {
        return configurations[x][y]
    }

    companion object {
        // chunk size in blocks
        const val WIDTH = 250
        const val HEIGHT = 500
    }
}
