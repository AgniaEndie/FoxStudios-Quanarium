package com.foxstudios.quanarium

import com.badlogic.gdx.Game
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Graphics.DisplayMode
import com.badlogic.gdx.Graphics.Monitor
import com.badlogic.gdx.Input.Keys
import com.badlogic.gdx.Net
import com.badlogic.gdx.Preferences
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.net.Socket
import com.badlogic.gdx.net.SocketHints
import com.foxstudios.quanarium.screen.MenuScreen


class Quanarium : Game() {
    var socketHints : SocketHints? = null
    var socket : Socket? = null
    var batch: SpriteBatch? = null
    var prefs: Preferences? = null
    var monitor : Monitor? = null
    var display  : DisplayMode? = null
    var atlas : TextureAtlas? = null
    override fun create() {
//        atlas = TextureAtlas("assets/gui/atlas.json")
        batch = SpriteBatch()
        prefs = Gdx.app.getPreferences("settings")
        monitor = Gdx.graphics.monitor
        display = Gdx.graphics.getDisplayMode(monitor)
        socketHints = SocketHints()
        socket = Gdx.net.newClientSocket(Net.Protocol.TCP, prefs!!.getString("ip"), prefs!!.getInteger("port"), this.socketHints)
        setScreen(MenuScreen(this))
    }

    fun toggleSettings(){
        toggleVsync()
        toggleFullscreen()
        toggleFPS()
    }

    fun toggleFPS(){
        Gdx.graphics.setForegroundFPS(prefs!!.getInteger("foreground_fps"))
    }

    fun toggleVsync(){
        Gdx.graphics.setVSync(prefs!!.getBoolean("use_vsync"))
    }

    fun toggleFullscreen(){


        if (Gdx.input.isKeyJustPressed(Keys.F11)) {
            if (Gdx.graphics.isFullscreen) {
                Gdx.graphics.setWindowedMode(800,480)
            } else {
                Gdx.graphics.setFullscreenMode(display)
            }
        }
    }
    override fun render() {
        super.render()
    }

    override fun dispose() {
        batch!!.dispose()
    }
}
