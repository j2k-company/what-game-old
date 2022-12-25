package com.j2k.whatgame

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.TextureRegion

object Assets {
    val block_dirt: TextureRegion = TextureRegion(
        Texture(Gdx.files.internal("block_dirt.png"))
    )
    val item_dirt: TextureRegion = TextureRegion(
        Texture(Gdx.files.internal("item_dirt.png"))
    )
}
