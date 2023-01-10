package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas

object AssetManager {
    private val atlas by lazy {
        TextureAtlas(Gdx.files.internal("texture-pack.atlas"))
    }

    val blockDirt by lazy { atlas.findRegions("block_dirt") }
    val blockOre  by lazy { atlas.findRegions("block_ore") }

    val itemDirt  by lazy { atlas.findRegion("item_dirt") }

    val player    by lazy { atlas.findRegion("player2d") }

    fun dispose() {
        atlas.dispose()
    }
}