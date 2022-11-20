package com.j2k.whatgame.input.inputmanager

import com.j2k.whatgame.input.InputSignal

class DesktopInputManager : InputManager() {
    private val _events = mutableListOf<InputSignal>()
    override val events: List<InputSignal> by this::_events

    override fun keyDown(keycode: Int): Boolean {
        val signal = InputSignal.getAtKey(keycode)

        if (signal != null) {
            _events.add(signal)
            return true
        }

        return false
    }

    override fun keyUp(keycode: Int): Boolean {
        val signal = InputSignal.getAtKey(keycode)

        if (signal != null) {
            _events.remove(signal)
            return true
        }

        return false
    }
}