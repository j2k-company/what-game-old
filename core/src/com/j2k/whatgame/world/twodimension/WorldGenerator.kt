package com.j2k.whatgame.world.twodimension

import com.j2k.whatgame.math.simplexnoise.SimplexNoise

class WorldGenerator(largestFeature: Int, persistence: Double, seed: Int) {
    private var noise: SimplexNoise =
        SimplexNoise(largestFeature, persistence, seed)

    fun generateChunk(chunkPosition: Int): TerrainChunk {
        val chunkPosition = chunkPosition * TerrainChunk.WIDTH * Block.SIZE

        val chunk = TerrainChunk()
        val vertices = Array(TerrainChunk.WIDTH + 1) {
            IntArray(TerrainChunk.HEIGHT + 1) { 0 }
        }

        for (x in 0..TerrainChunk.WIDTH) {
            for (y in 0..TerrainChunk.HEIGHT) {
                val xPos = x * Block.SIZE + chunkPosition
                val yPos = y * Block.SIZE

                // TODO: remove magic values
                // 250 - is the height of the noise zero point
                // 200 - magic coef for values in the range (-1, 1)
                if (yPos > noise.getNoise(xPos, 0) * 200 + 250) {
                    vertices[x][y] = 0
                } else {
                    vertices[x][y] = 1
                }
            }
        }

        val configurations = MarchingSquares.getConfigurations(vertices.map(IntArray::toList))
        val blocks = Array(TerrainChunk.WIDTH) { IntArray(TerrainChunk.HEIGHT) { 0 } }

        // TODO: add generation of different resources and structures

        for (x in 0 until TerrainChunk.WIDTH) {
            for (y in 0 until TerrainChunk.HEIGHT) {
                println("$x, $y")
                chunk.setBlock(blocks[x][y], configurations[x][y], x, y)
            }
        }

        return chunk
    }
}
