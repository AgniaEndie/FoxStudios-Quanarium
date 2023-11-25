package com.foxstudios.quanarium

import com.badlogic.gdx.ApplicationAdapter
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.ScreenUtils
import com.foxstudios.quanarium.entity.Player

class Quanarium : ApplicationAdapter() {
    var batch: SpriteBatch? = null
    val players: ArrayList<Player> = ArrayList()
    override fun create() {
        batch = SpriteBatch()
        players.add(Player())
    }

    override fun render() {
        ScreenUtils.clear(1f, 0f, 0f, 1f)
        batch!!.begin()
        for (player in players) {
            player.render(batch!!)
        }
        batch!!.end()
    }

    override fun dispose() {
        batch!!.dispose()
        for (player in players) {
            player.dispose(players)
        }
    }
}
