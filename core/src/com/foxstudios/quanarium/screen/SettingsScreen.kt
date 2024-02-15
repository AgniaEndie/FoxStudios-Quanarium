package com.foxstudios.quanarium.screen

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.*
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener
import com.badlogic.gdx.utils.ScreenUtils
import com.foxstudios.quanarium.Quanarium

class SettingsScreen(private val game: Quanarium) : Screen {
    private var globalCamera: OrthographicCamera? = null
    private var prefs: Preferences? = null
    private var table: Table? = null
    private var labelStyle: Label.LabelStyle? = null
    private var stage: Stage? = null
    private var skin: Skin? = null
    private var buttonStyle: TextButton.TextButtonStyle? = null
    private var vsyncBtn: TextButton? = null
    private var nicknameInput: TextField? = null
    private var nickSaveBtn: TextButton? = null
    override fun show() {
        prefs = Gdx.app.getPreferences("settings")
        stage = Stage()
        skin = Skin(Gdx.files.internal("assets/gui/atlas.json"))

        globalCamera = OrthographicCamera(800f, 480f)
        labelStyle = Label.LabelStyle()
        labelStyle!!.font = BitmapFont()
        table = Table()
        table!!.setFillParent(true)
        stage!!.addActor(table)
        buttonStyle = TextButton.TextButtonStyle()
        buttonStyle!!.font = BitmapFont()
        table!!.add(Label("Settings", labelStyle)).padBottom(10f).colspan(2)
        table!!.row()
        table!!.debug=true
        vsyncBtn = addButtonedSetting("use vsync", "use_vsync", false)
        vsyncBtn!!.addListener(object : ClickListener() {
            override fun clicked(event: InputEvent?, x: Float, y: Float) {
                val old = prefs!!.getBoolean("use_vsync", false)
                if (old) {
                    prefs!!.putBoolean("use_vsync", false)
                } else {
                    prefs!!.putBoolean("use_vsync", true)
                }
                prefs!!.flush()
                print("check")
            }
        })
        nicknameInput = addInputButton("test", "test","test123","test123","test123")
        nickSaveBtn = saveButton(nicknameInput,"test")
        nickSaveBtn!!.addListener (
            object: ClickListener(){
                override fun clicked(event: InputEvent?, x: Float, y: Float) {
                    prefs!!.putString("test", nicknameInput!!.text)
                    prefs!!.flush()
                    print("dada")
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
        updateButton(vsyncBtn, "use_vsync")
        //inp!!.draw(game.batch, 0.7f)
        game.batch!!.end()
    }

    private fun addButtonedSetting(text: String, key: String, default: Boolean): TextButton {
        val current = prefs!!.getBoolean(key, default)
        val btn = TextButton(current.toString(), buttonStyle)
        table!!.add(Label(text, labelStyle)).padBottom(10f).padRight(10f)
        table!!.add(btn).padBottom(10f)
        table!!.row()
        prefs!!.flush()
        return btn
    }

    private fun addInputButton(text: String, key: String, default: String, title:String, hint:String): TextField {
        val currentValue = prefs!!.getString(key, default)
        //val btn = TextButton(currentValue.toString(), buttonStyle)
        val test = TextField(currentValue, skin)
        if(currentValue.isNotEmpty() && currentValue != ""){
            test.text = currentValue
        }else{
            test.text = default
        }
        test.setOnscreenKeyboard {
            TextField.OnscreenKeyboard {
                Gdx.input.getTextInput(
                    object: Input.TextInputListener {
                        override fun input(text: String?) {
                            test.text = text
                            println("save")
                            prefs!!.putString(key, text)
                            prefs!!.flush()
                        }

                        override fun canceled() {

                        }
                    }, title,default,hint)
            }
        }
        table!!.add(Label(text, labelStyle)).padBottom(10f).padRight(10f)
        table!!.add(test).padBottom(10f)
        table!!.row()
        return test
    }

    fun saveButton(textField: TextField?, key:String): TextButton {
        val btn = TextButton("save", buttonStyle)
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
