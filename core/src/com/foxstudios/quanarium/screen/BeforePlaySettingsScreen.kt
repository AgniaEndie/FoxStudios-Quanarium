package com.foxstudios.quanarium.screen

import com.badlogic.gdx.*
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.ScreenUtils
import com.foxstudios.quanarium.Quanarium


class BeforePlaySettingsScreen(private val game: Quanarium) : Screen {
    private var globalCamera: OrthographicCamera? = null
    private var prefs: Preferences? = null
    private var table: Table? = null
    private var labelStyle: Label.LabelStyle? = null
    private var stage: Stage? = null
    private var skin: Skin? = null
    private var buttonStyle: TextButton.TextButtonStyle? = null
    private var addressInput: TextField? = null
    private var portInput: TextField? = null
    private var saveBtn: TextButton? = null

    override fun show() {
        prefs = Gdx.app.getPreferences("settings")
        stage = Stage()
        skin = Skin(Gdx.files.internal("assets/gui/atlas.json"))

        globalCamera = OrthographicCamera(800f, 480f)
        labelStyle = Label.LabelStyle()
        labelStyle!!.font = BitmapFont()
        table = Table()
        table!!.debug = true;
        table!!.setFillParent(true)
        stage!!.addActor(table)
        buttonStyle = TextButton.TextButtonStyle()
        buttonStyle!!.font = BitmapFont()
        table!!.add(Label("ServerAddress", labelStyle)).padBottom(10f).colspan(2)
        table!!.row()

        addressInput = addInputButton("address", "ip", "127.0.0.1", "address", "address")
        val regex = Gdx.files.internal("assets/regex.txt").readString()
        Gdx.app.log("debug", regex)
//        addressInput!!.setTextFieldFilter(TextFieldFilter { textField, c ->
//            if (c.toString().matches((regex).toRegex())) true else false
//        })

        table!!.row()
        portInput = addInputButton("port", "port", "8080", "port", "port")
        table!!.row()

        saveBtn = saveButton(addressInput, "ip")
        saveBtn!!.addListener(
            object : ClickListener() {
                override fun clicked(event: InputEvent?, x: Float, y: Float) {
                    prefs!!.putString("ip", addressInput!!.text)
                    prefs!!.putInteger("port", portInput!!.text.toInt())
                    prefs!!.flush()
                    Gdx.app.applicationLogger.log("network", "${addressInput!!.text}:${portInput!!.text.toInt()}")
                    game.socket = Gdx.net.newClientSocket(
                        Net.Protocol.TCP,
                        addressInput!!.text,
                        portInput!!.text.toInt(),
                        game.socketHints
                    )
                }
            }
        )

        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        game.toggleSettings()
        globalCamera!!.update()
        game.batch!!.projectionMatrix = globalCamera!!.combined
        game.batch!!.begin()
        ScreenUtils.clear(0f, 208f, 255f, 1f)
        stage!!.act()
        stage!!.draw()
        //inp!!.draw(game.batch, 0.7f)
        game.batch!!.end()
    }


    private fun addInputButton(
        text: String,
        key: String,
        default: String,
        title: String,
        hint: String,
        pattern: String = ""
    ): TextField {
        val currentValue = prefs!!.getString(key, default)
        //val btn = TextButton(currentValue.toString(), buttonStyle)
        val test = TextField(currentValue, skin)
        if (currentValue.isNotEmpty() && currentValue != "") {
            test.text = currentValue
        } else {
            test.text = default
        }
        test.setOnscreenKeyboard {
            TextField.OnscreenKeyboard {
                Gdx.input.getTextInput(
                    object : Input.TextInputListener {
                        override fun input(text: String?) {
                            test.text = text
                            println("save")
                            prefs!!.putString(key, text)
                            prefs!!.flush()
                        }

                        override fun canceled() {

                        }
                    }, title, default, hint
                )
            }
        }


        table!!.add(Label(text, labelStyle))
        table!!.add(test).padBottom(10f)
        //stage!!.addActor(test)
        return test
    }

    fun saveButton(textField: TextField?, key: String): TextButton {
        val btn = TextButton("play", buttonStyle)
        table!!.add(btn).padBottom(10f)
        table!!.row()
        return btn
    }

    private fun updateButton(btn: TextButton?, key: String) {
        btn!!.label = Label(prefs!!.getBoolean(key).toString(), labelStyle)
    }

    override fun resize(width: Int, height: Int) {

    }

    override fun pause() {

    }

    override fun resume() {

    }

    override fun hide() {

    }

    override fun dispose() {
        game.dispose()
    }
}
