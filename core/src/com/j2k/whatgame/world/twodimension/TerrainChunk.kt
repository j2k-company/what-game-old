package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.graphics.g2d.SpriteBatch


// chunk size in blocks
const val CHUNK_WIDTH = 250
const val CHUNK_HEIGHT = 500

class TerrainChunk(val position: Int) {
    // array with values 0..15
    private val configurations = Array(CHUNK_WIDTH) { IntArray(CHUNK_HEIGHT) { 0 } }

    // array with object ids in the Block enum
    private val blocks = Array(CHUNK_WIDTH) { IntArray(CHUNK_HEIGHT) { 0 } }

    fun render(batch: SpriteBatch) {
        for (x in 0 until CHUNK_WIDTH) {
            for (y in 0 until CHUNK_HEIGHT) {
                val conf = configurations[x][y]
                val texture = Block.values()[blocks[x][y]].getTexture(conf)

                batch.draw(
                    texture,
                    (x * BLOCK_SIZE + position * BLOCK_SIZE * CHUNK_WIDTH).toFloat(),
                    (y * BLOCK_SIZE).toFloat(),
                    BLOCK_SIZE.toFloat(), BLOCK_SIZE.toFloat()
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
}
