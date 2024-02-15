package com.foxstudios.quanarium.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.foxstudios.quanarium.control.KeyBoardListener
import java.util.UUID
import kotlin.random.Random

class Player {
    private val skin: Texture = Texture("entity/Player${Random.nextInt(1, 2)}.png")
    private val nickname: String = "player${Random.nextInt(1, 100)}"
    private val uuid: UUID = UUID.randomUUID()
    private val keyboardListener = KeyBoardListener()
    private var x: Float = 0f
    private var y: Float = 0f
    private val speed: Float = 25f
    private val sprintSpeed: Float = 45f
    private val font = BitmapFont()
    private var camera: OrthographicCamera? = OrthographicCamera(x, y)
    fun render(batch: SpriteBatch) {
        batch.draw(skin, x, y)
        move(batch, camera!!)
        camera!!.update()
    }

    private fun getLocalSpeed(): Float {
        return if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            sprintSpeed
        } else {
            speed
        }
    }

    private fun move(batch: SpriteBatch, camera: OrthographicCamera) {
        val localSpeed = getLocalSpeed()
        if (keyboardListener.isWKeyPressed()) {
            y += Gdx.graphics.deltaTime * localSpeed
        }
        if (keyboardListener.isAKeyPressed()) {
            x -= Gdx.graphics.deltaTime * localSpeed
        }
        if (keyboardListener.isSKeyPressed()) {
            y -= Gdx.graphics.deltaTime * localSpeed
        }
        if (keyboardListener.isDKeyPressed()) {
            x += Gdx.graphics.deltaTime * localSpeed
        }
        camera.position.set(x, y, 0f)
        font.draw(batch, nickname, x, y + 50)
        batch.draw(skin, x, y + 51)

        Gdx.app.applicationLogger.log("Players", "pos of player: $nickname X:$x , Y:$y , uuid: $uuid")

    }

    fun dispose(players: ArrayList<Player>) {
        skin.dispose()
        //players.remove(return players.forEach { player: Player -> player.uuid == uuid })
    }

}
