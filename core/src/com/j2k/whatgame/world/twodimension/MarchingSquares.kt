package com.j2k.whatgame.world.twodimension

class MarchingSquares {

    fun threshold(
        data: List<List<Byte>>,
        threshold: Byte
    ): List<List<Int>> = data.map {
        it.map { value ->
            if (value > threshold) 1 else 0
        }
    }

    fun getConfigurations(data: List<List<Int>>): List<Int> {
        val configurations = mutableListOf<Int>()

        val xSize = data.lastIndex
        val ySize = data.first().lastIndex

        for (x in 0 until xSize) {
            for (y in 0 until ySize) {
                configurations.add(
                    8 * data[x][y] + 4 * data[x + 1][y] + 2 * data[x + 1][y + 1] + data[x][y + 1]
                )
            }
        }

        return configurations
    }

    companion object {
        val contours = listOf(
            listOf(listOf()), // 0000 configuration
            listOf(listOf(
                0f, 0f,
                0.5f, 0f,
                0f, 0.5f
            )), // 0001 configuration
            listOf(listOf(
                0.5f, 0f,
                1f, 0f,
                1f, 0.5f
            )), // 0010 configuration
            listOf(listOf(
                0f, 0f,
                1f, 0f,
                1f, 0.5f,
                0f, 0.5f
            )), // 0011 configuration
            listOf(listOf(
                1f, 0.5f,
                1f, 1f,
                0,5f, 1f
            )), // 0100 configuration
            listOf(listOf(
                0f, 0f,
                0.5f, 0f,
                0f, 0.5f
            ), listOf(
                1f, 0.5f,
                1f, 1f,
                0,5f, 1f
            )), // 0101 configuration
            listOf(listOf(
                0.5f, 0f,
                1f, 0f,
                1f, 1f,
                0.5f, 1f
            )), // 0110 configuration
            listOf(listOf(
                0f, 0f,
                1f, 0f,
                1f, 1f,
                0.5f, 1f,
                0f, 0.5f
            )), // 0111 configuration
            listOf(listOf(
                0f, 0.5f,
                0.5f, 1f,
                0f, 1f
            )), // 1000 configuration
            listOf(listOf(
                0f, 0f,
                0.5f, 0f,
                0.5f, 1f,
                0f, 1f
            )), // 1001 configuration
            listOf(listOf(
                0.5f, 0f,
                1f, 0f,
                1f, 0.5f
            ), listOf(
                0f, 0.5f,
                0.5f, 1f,
                0f, 1f
            )), // 1010 configuration
            listOf(listOf(
                0f, 0f,
                1f, 0f,
                1f, 0.5f,
                0.5f, 1f,
                0f, 1f
            )), // 1011 configuration
            listOf(listOf(
                0f, 0.5f,
                1f, 0.5f,
                1f, 1f,
                0f, 1f
            )), // 1100 configuration
            listOf(listOf(
                0f, 0f,
                0.5f, 0f,
                1f, 0.5f,
                1f, 1f,
                0f, 1f
            )), // 1101 configuration
            listOf(listOf(
                0.5f, 0f,
                1f, 0f,
                1f, 1f,
                0f, 1f,
                0f, 0.5f
            )), // 1110 configuration
            listOf(listOf(
                0f, 0f,
                1f, 0f,
                1f, 1f,
                0f, 1f
            )), // 1111 configuration
        )
    }
}
