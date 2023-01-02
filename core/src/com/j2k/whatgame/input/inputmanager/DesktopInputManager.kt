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

    override fun scrolled(amountX: Float, amountY: Float): Boolean {
        if(amountY == 1f) {
            _events.add(InputSignal.SCROLLED_DOWN)
        } else if (amountY == -1f) {
            _events.add(InputSignal.SCROLLED_UP)
        } else {
            return false
        }
        return true
    }

    override fun removeTemporaryEvents() {
        _events.removeIf {
            when(it) {
                InputSignal.SCROLLED_DOWN -> true
                InputSignal.SCROLLED_UP -> true
                else -> false
            }
        }
    }
}
