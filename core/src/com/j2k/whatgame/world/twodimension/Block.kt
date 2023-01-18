package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.utils.Array


const val BLOCK_SIZE = 1

enum class Block(private val textures: Array<TextureAtlas.AtlasRegion>, val item: Item?) {
    DIRT(AssetManager.blockDirt, Item.DIRT), ORE(AssetManager.blockOre, null);

    fun getTexture(conf: Int): TextureAtlas.AtlasRegion =
        textures.get(conf)

}