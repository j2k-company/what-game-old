package com.j2k.whatgame.input.inputmanager

import com.j2k.whatgame.input.InputSignal

class AndroidInputManager : InputManager() {
    private val _events = mutableListOf<InputSignal>()
    override val events: List<InputSignal> by this::_events
}
