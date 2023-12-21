package com.foxstudios.quanarium

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.foxstudios.quanarium.screen.MenuScreen

class Quanarium : Game() {
    var batch: SpriteBatch? = null

    override fun create() {
        batch = SpriteBatch()
        setScreen(MenuScreen(this))
    }

    override fun render() {
        super.render()
    }

    override fun dispose() {
        //Dispose Assets which you used in the whole Game
        batch!!.dispose()
    }
}
