package com.foxstudios.quanarium.control

import com.badlogic.gdx.Input

class QuanariumTextInputListener : Input.TextInputListener {
    override fun input(text: String?) {
        println(text)
    }

    override fun canceled() {
        println("cancelled")
    }

}


