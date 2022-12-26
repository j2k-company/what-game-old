package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.j2k.whatgame.Assets

enum class Block(private val textures: List<TextureRegion>, val item: Item?) {
    DIRT(listOf(Assets.blockDirt), Item.DIRT);

    fun getTexture(conf: Int): TextureRegion =
        textures.getOrElse(conf) { textures.first() }

    companion object {
        const val SIZE = 16
    }
}
