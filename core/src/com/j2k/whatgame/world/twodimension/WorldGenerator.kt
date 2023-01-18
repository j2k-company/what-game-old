package com.j2k.whatgame.world.twodimension

import com.j2k.whatgame.math.simplexnoise.SimplexNoise

class WorldGenerator(largestFeature: Int, persistence: Double, seed: Int) {
    private var noise: SimplexNoise =
        SimplexNoise(largestFeature, persistence, seed)

    @Suppress("NAME_SHADOWING")
    fun generateChunk(chunkPosition: Int): TerrainChunk {
        val chunk = TerrainChunk(chunkPosition)
        val chunkPosition = chunkPosition * CHUNK_WIDTH * BLOCK_SIZE

        val vertices = Array(CHUNK_WIDTH + 1) {
            IntArray(CHUNK_HEIGHT + 1) { 0 }
        }

        for (x in 0..CHUNK_WIDTH) {
            for (y in 0..CHUNK_HEIGHT) {
                val xPos = x * BLOCK_SIZE + chunkPosition
                val yPos = y * BLOCK_SIZE

                // TODO: remove magic values
                // 250 - is the height of the noise zero point
                // 200 - magic coefficient for values in the range (-1, 1)
                if (yPos > noise.getNoise(xPos, 0) * 200 + 250 * BLOCK_SIZE) {
                    vertices[x][y] = 0
                } else {
                    vertices[x][y] = 1
                }
            }
        }

        val configurations = MarchingSquares.getConfigurations(vertices.map(IntArray::toList))
        val blocks = Array(CHUNK_WIDTH) { IntArray(CHUNK_HEIGHT) { 0 } }

        // TODO: add generation of different resources and structures

        for (x in 0 until CHUNK_WIDTH) {
            for (y in 0 until CHUNK_HEIGHT) {
                if ((0..CHUNK_HEIGHT).random() < CHUNK_HEIGHT /(y + 1)) {
                    blocks[x][y] = 1
                }
                chunk.setBlock(blocks[x][y], configurations[x][y], x, y)
            }
        }

        return chunk
    }
}
