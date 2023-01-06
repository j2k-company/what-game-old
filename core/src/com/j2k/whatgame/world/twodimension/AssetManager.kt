package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.TextureAtlas.AtlasRegion
import com.badlogic.gdx.utils.Array

object AssetManager {
    private val atlas: TextureAtlas by lazy {
        TextureAtlas(Gdx.files.internal("texture-pack.atlas"))
    }

    val blockDirt: Array<AtlasRegion> by lazy { atlas.findRegions("block_dirt") }
    val blockOre: Array<AtlasRegion> by lazy { atlas.findRegions("block_ore") }

    val itemDirt: AtlasRegion by lazy { atlas.findRegion("item_dirt") }

    val player: AtlasRegion by lazy { atlas.findRegion("player2d") }

    fun dispose() {
        atlas.dispose()
    }
}