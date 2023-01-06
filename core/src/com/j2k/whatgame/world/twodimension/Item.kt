package com.j2k.whatgame.world.twodimension

import com.badlogic.gdx.graphics.g2d.TextureRegion

enum class Item(val title: String, val texture: TextureRegion) {
    DIRT("Dirt", AssetManager.itemDirt)
}
