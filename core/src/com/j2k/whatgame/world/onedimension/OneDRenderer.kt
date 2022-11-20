package com.j2k.whatgame.world.onedimension

import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.j2k.whatgame.Entity
import com.j2k.whatgame.Renderer
import com.j2k.whatgame.world.onedimension.entities.OneDEntity
import space.earlygrey.shapedrawer.ShapeDrawer


class OneDRenderer(
    private val worldLength: Float,
    private val shapeDrawer: ShapeDrawer
) : Renderer {
    init {
        shapeDrawer.defaultLineWidth = 30f
    }

    override fun render(batch: SpriteBatch, entities: Array<Entity>) {
        shapeDrawer.apply {
            setColor(0f, 0f, 0f, 0.5f)
            line(-worldLength/2, 0f, worldLength/2, 0f)
        }

        for(entity in entities) {
            if(entity is OneDEntity) {
                entity.render(shapeDrawer)
            }
        }
    }
}