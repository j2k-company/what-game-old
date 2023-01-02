package com.j2k.whatgame.input.inputmanager

import com.badlogic.gdx.InputAdapter
import com.j2k.whatgame.input.InputSignal

abstract class InputManager : InputAdapter() {
    abstract val events: List<InputSignal>
    abstract fun removeTemporaryEvents()
}
