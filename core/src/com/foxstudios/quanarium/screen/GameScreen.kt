package com.foxstudios.quanarium.screen

import com.badlogic.gdx.Screen
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.foxstudios.quanarium.Quanarium
import com.foxstudios.quanarium.entity.Player

class GameScreen(private val game: Quanarium) : Screen {

    private val players: ArrayList<Player> = ArrayList()
    private var testImage: Texture? = null
    private var globalCamera: OrthographicCamera? = null
    private var player1 : Player? = null
    override fun show() {
        globalCamera = OrthographicCamera(800f,480f)
        testImage = Texture("logo.png")
        player1 = Player()
        players.add(player1!!)
    }

    override fun render(delta: Float) {
        globalCamera!!.update()
        game.batch!!.projectionMatrix = globalCamera!!.combined
        game.batch!!.begin()
        game.batch!!.draw(testImage, 0f, 0f)
        for (player in players) {
            player.render(game.batch!!)
        }
        game.batch!!.end()
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
        game.batch!!.dispose()
//      for (player in players) {
//          player.dispose(players)
//      }
        game.batch!!.dispose()
    }
}
