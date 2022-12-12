package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.PolygonRegion
import com.badlogic.gdx.graphics.g2d.PolygonSprite
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.badlogic.gdx.math.Vector2

object MarchingSquares {

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

    fun drawSquare(
        configuration: Int,
        texture: Texture,
        size: Float,
        polyBatch: PolygonSpriteBatch,
        pos: Vector2 = Vector2(0.0f, 0.0f)
    ) {
        val polygons = getSquareSprites(configuration, texture, size)

        polygons.forEach { poly ->
            poly.setPosition(pos.x, pos.y)
            poly.draw(polyBatch)
        }
    }

    fun getSquareSprites(
        configuration: Int,
        texture: Texture,
        size: Float,
    ): List<PolygonSprite> {
        val sprites = mutableListOf<PolygonSprite>()

        contours[configuration].forEach { figure ->
            val polyReg = PolygonRegion(
                TextureRegion(texture),
                figure.first.map {
                    it * size
                }.toFloatArray(), figure.second
            )

            sprites.add(PolygonSprite(polyReg))
        }

        return sprites
    }


    private val contours = listOf(
        listOf(Pair(floatArrayOf(), shortArrayOf())), // 0000 configuration
        listOf(
            Pair(
                floatArrayOf(
                    0f, 0f,
                    0.5f, 0f,
                    0f, 0.5f
                ), shortArrayOf(
                    0, 1, 2
                )
            )
        ), // 0001 configuration
        listOf(
            Pair(
                floatArrayOf(
                    0.5f, 0f,
                    1f, 0f,
                    1f, 0.5f
                ), shortArrayOf(
                    0, 1, 2
                )
            )
        ), // 0010 configuration
        listOf(
            Pair(
                floatArrayOf(
                    0f, 0f,
                    1f, 0f,
                    1f, 0.5f,
                    0f, 0.5f
                ), shortArrayOf(
                    0, 1, 2,
                    0, 2, 3
                )
            )
        ), // 0011 configuration
        listOf(
            Pair(
                floatArrayOf(
                    1f, 0.5f,
                    1f, 1f,
                    0.5f, 1f
                ), shortArrayOf(
                    0, 1, 2
                )
            )
        ), // 0100 configuration
        listOf(
            Pair(
                floatArrayOf(
                    0f, 0f,
                    0.5f, 0f,
                    0f, 0.5f
                ), shortArrayOf(
                    0, 1, 2
                )
            ), Pair(
                floatArrayOf(
                    1f, 0.5f,
                    1f, 1f,
                    0.5f, 1f
                ), shortArrayOf(
                    0, 1, 2
                )
            )
        ), // 0101 configuration
        listOf(
            Pair(
                floatArrayOf(
                    0.5f, 0f,
                    1f, 0f,
                    1f, 1f,
                    0.5f, 1f
                ), shortArrayOf(
                    0, 1, 2,
                    0, 2, 3
                )
            )
        ), // 0110 configuration
        listOf(
            Pair(
                floatArrayOf(
                    0f, 0f,
                    1f, 0f,
                    1f, 1f,
                    0.5f, 1f,
                    0f, 0.5f
                ), shortArrayOf(
                    0, 1, 4,
                    1, 3, 4,
                    1, 2, 3
                )
            )
        ), // 0111 configuration
        listOf(
            Pair(
                floatArrayOf(
                    0f, 0.5f,
                    0.5f, 1f,
                    0f, 1f
                ), shortArrayOf(
                    0, 1, 2
                )
            )
        ), // 1000 configuration
        listOf(
            Pair(
                floatArrayOf(
                    0f, 0f,
                    0.5f, 0f,
                    0.5f, 1f,
                    0f, 1f
                ), shortArrayOf(
                    0, 1, 2,
                    0, 2, 3
                )
            )
        ), // 1001 configuration
        listOf(
            Pair(
                floatArrayOf(
                    0.5f, 0f,
                    1f, 0f,
                    1f, 0.5f
                ), shortArrayOf(
                    0, 1, 2
                )
            ), Pair(
                floatArrayOf(
                    0f, 0.5f,
                    0.5f, 1f,
                    0f, 1f
                ), shortArrayOf(
                    0, 1, 2
                )
            )
        ), // 1010 configuration
        listOf(
            Pair(
                floatArrayOf(
                    0f, 0f,
                    1f, 0f,
                    1f, 0.5f,
                    0.5f, 1f,
                    0f, 1f
                ), shortArrayOf(
                    0, 1, 2,
                    0, 2, 3,
                    0, 3, 4
                )
            )
        ), // 1011 configuration
        listOf(
            Pair(
                floatArrayOf(
                    0f, 0.5f,
                    1f, 0.5f,
                    1f, 1f,
                    0f, 1f
                ), shortArrayOf(
                    0, 1, 2,
                    0, 2, 3
                )
            )
        ), // 1100 configuration
        listOf(
            Pair(
                floatArrayOf(
                    0f, 0f,
                    0.5f, 0f,
                    1f, 0.5f,
                    1f, 1f,
                    0f, 1f
                ), shortArrayOf(
                    0, 1, 4,
                    1, 2, 4,
                    2, 3, 4
                )
            )
        ), // 1101 configuration
        listOf(
            Pair(
                floatArrayOf(
                    0.5f, 0f,
                    1f, 0f,
                    1f, 1f,
                    0f, 1f,
                    0f, 0.5f
                ), shortArrayOf(
                    0, 1, 2,
                    0, 2, 4,
                    4, 2, 3
                )
            )
        ), // 1110 configuration
        listOf(
            Pair(
                floatArrayOf(
                    0f, 0f,
                    1f, 0f,
                    1f, 1f,
                    0f, 1f
                ), shortArrayOf(
                    0, 1, 2,
                    0, 2, 3
                )
            )
        ), // 1111 configuration
    )

}
