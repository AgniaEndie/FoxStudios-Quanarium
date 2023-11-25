package com.foxstudios.quanarium.control

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input.Keys
class KeyBoardListener {
    fun isWKeyPressed ():Boolean{
        return Gdx.input.isKeyPressed(Keys.W)
    }
    fun isAKeyPressed ():Boolean{
        return Gdx.input.isKeyPressed(Keys.A)
    }
    fun isSKeyPressed ():Boolean{
        return Gdx.input.isKeyPressed(Keys.S)
    }
    fun isDKeyPressed ():Boolean{
        return Gdx.input.isKeyPressed(Keys.D)
    }
}
