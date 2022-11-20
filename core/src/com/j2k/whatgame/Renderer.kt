package com.j2k.whatgame

import com.badlogic.gdx.graphics.g2d.SpriteBatch

interface Renderer {
    fun render(batch: SpriteBatch, entities: Array<Entity> = emptyArray())
}
