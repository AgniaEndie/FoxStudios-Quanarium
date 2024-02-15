package com.foxstudios.quanarium

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration

class DesktopLauncher {

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val config = Lwjgl3ApplicationConfiguration()
            config.setForegroundFPS(60)
            config.setTitle("Quanarium")
            config.setWindowIcon("assets/logo.png")
            config.setWindowedMode(800,480)
            Lwjgl3Application(Quanarium(), config)
        }
    }
}
