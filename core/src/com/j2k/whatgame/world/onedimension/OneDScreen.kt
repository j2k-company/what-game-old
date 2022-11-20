package com.j2k.whatgame.world.onedimension

import com.badlogic.gdx.graphics.*
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureRegion
import com.j2k.whatgame.Entity
import com.j2k.whatgame.WorldScreen
import com.j2k.whatgame.world.onedimension.entities.Enemy
import com.j2k.whatgame.world.onedimension.entities.OneDPlayer
import space.earlygrey.shapedrawer.ShapeDrawer


class OneDScreen(override val batch: SpriteBatch) : WorldScreen() {
    private val texture: Texture
    private val shapeDrawer: ShapeDrawer

    lateinit var camera: Camera

    override val renderer: OneDRenderer
    override val player: OneDPlayer = OneDPlayer(-50f, 50f, 50f)

    init {
        val pixmap = Pixmap(1, 1, Pixmap.Format.RGBA8888)
        pixmap.setColor(Color.WHITE)
        pixmap.drawPixel(0, 0)
        texture = Texture(pixmap)

        val region = TextureRegion(texture, 0, 0, 1, 1)

        pixmap.dispose()
        shapeDrawer = ShapeDrawer(batch, region)

        renderer = OneDRenderer(800f, shapeDrawer)
    }

    private val entities = arrayOf<Entity>(Enemy(5f, 10f, 50f), player)

    override fun show() {
        camera = OrthographicCamera(800f, 800f)
    }


    override fun render(delta: Float) {
        batch.projectionMatrix = camera.combined

        batch.begin()
        renderer.render(batch, entities)
        batch.end()
    }

    override fun dispose() {
        texture.dispose()
        super.dispose()
    }
}
