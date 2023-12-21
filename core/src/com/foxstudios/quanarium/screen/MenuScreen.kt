package com.foxstudios.quanarium.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.ScreenAdapter
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Label
import com.badlogic.gdx.scenes.scene2d.ui.Label.LabelStyle
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Table
import com.badlogic.gdx.scenes.scene2d.ui.TextButton
import com.badlogic.gdx.scenes.scene2d.ui.TextButton.TextButtonStyle
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.ScreenUtils
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.Viewport
import com.foxstudios.quanarium.Quanarium

class MenuScreen(private val game: Quanarium) : ScreenAdapter() {
    private var buttonStyle: TextButtonStyle = TextButtonStyle()
    private var skin: Skin? = null
    private var stage: Stage? = null
    private lateinit var viewport: Viewport
    private var table: Table? = null
    private var labelStyle: LabelStyle? = null
    private var menuCam: OrthographicCamera? = null
    override fun show() {
        menuCam = OrthographicCamera()
        menuCam!!.setToOrtho(false, 800f, 480f)
        labelStyle = LabelStyle()
        labelStyle!!.font = BitmapFont()
        stage = Stage()
        skin = Skin()
        viewport = ExtendViewport(800f, 400f)
        table = Table()
        table!!.setFillParent(true)
        stage!!.addActor(table)
        buttonStyle.font = BitmapFont()
        table!!.add(Label("Quanarium", labelStyle)).padBottom(10f)
        table!!.row()
        addButton("Play!").addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
//                game.screen = GameScreen(game)
//                dispose()
            }
        })
        addButton("Settings").addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                println("Settings Screen")
            }
        })
        addButton("Quit").addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                Gdx.app.exit()
            }
        })
        Gdx.input.inputProcessor = stage
    }

    private fun addButton(name: String): TextButton {
        val btn = TextButton(name, buttonStyle)
        table!!.add(btn).padBottom(10f)
        table!!.row()
        return btn
    }
    var f = 0f
    override fun render(delta: Float) {
        game.batch!!.begin()
        ScreenUtils.clear(0f, 208f, 255f, 1f)
        menuCam!!.update()
        f += delta
        println(f)
        stage!!.act()
        stage!!.draw()
        if (Gdx.input.isTouched) {
            game.screen = GameScreen(game)
            dispose()
        }
        game.batch!!.end()
    }

    override fun pause() {
        game.pause()
    }

    override fun resume() {
        game.resume()
    }

    override fun hide() {

    }

    override fun dispose() {
        super.dispose()
    }

}
