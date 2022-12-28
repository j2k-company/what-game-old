package com.j2k.whatgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

object Assets {
    val twoDPlayer = TextureRegion(
        Texture(Gdx.files.internal("player2d.png"))
    )
    val blockDirt = TextureRegion(
        Texture(Gdx.files.internal("block_dirt.png"))
    )
    val blockOre = TextureRegion(
        Texture(Gdx.files.internal("block_ore.png"))
    )
    val itemDirt = TextureRegion(
        Texture(Gdx.files.internal("item_dirt.png"))
    )
}
