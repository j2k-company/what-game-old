package com.j2k.whatgame.world.twodimension

object MarchingSquares {
    fun getConfigurations(data: List<List<Int>>): List<List<Int>> {
        val configurations = mutableListOf<List<Int>>()

        val xSize = data.lastIndex
        val ySize = data.first().lastIndex

        for (x in 0 until xSize) {
            val rowConf = mutableListOf<Int>()

            for (y in 0 until ySize) {
                // NOTE: if the top of the relief is inverted
                // uncomment the next line and comment out a similar one
                rowConf.add(
                    // data[x][y] + 2 * data[x + 1][y] + 4 * data[x + 1][y + 1] + 8 * data[x][y + 1]
                     data[x][y + 1] + 2 * data[x + 1][y + 1] + 4 * data[x + 1][y] + 8 * data[x][y]
                )
            }
            configurations.add(rowConf)
        }

        return configurations
    }
}
