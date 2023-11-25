package com.foxstudios.quanarium.entity

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.foxstudios.quanarium.control.KeyBoardListener
import java.util.UUID
import kotlin.random.Random

class Player() {
    val skin: Texture = Texture("entity/Player${Random.nextInt(1, 2)}.png")
    val nickname: String = "player${Random.nextInt(1, 100)}"
    val uuid: UUID = UUID.randomUUID()
    val keyboardListener = KeyBoardListener()
    var x: Float = 0f
    var y: Float = 0f
    val speed: Float = 25f
    val sprintSpeed: Float = 45f
    val font = BitmapFont()
    fun render(batch: SpriteBatch) {
        batch.draw(skin, x, y)
        move(batch)
    }

    fun getLocalSpeed(): Float {
        return if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            sprintSpeed
        } else {
            speed
        }
    }

    fun move(batch: SpriteBatch) {
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
        font.draw(batch, nickname, x, y + 50)
        batch.draw(skin, x, y)
        Gdx.app.applicationLogger.log("Players","pos of player ${nickname} X:${x} , Y:${y} , uuid: ${uuid}")

    }

    fun dispose(players: ArrayList<Player>) {
        skin.dispose()
        players.remove(return players.forEach({ player: Player -> player.uuid == uuid }))
    }

}
