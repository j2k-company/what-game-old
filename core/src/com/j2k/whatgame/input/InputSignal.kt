package com.j2k.whatgame.input

import com.badlogic.gdx.Input

enum class InputSignal {
    PLAYER_LEFT_MOVE,
    PLAYER_RIGHT_MOVE,
    PLAYER_UP_MOVE,
    PLAYER_DOWN_MOVE;

    companion object Mapper {
        fun getAtKey(keycode: Int): InputSignal? {
            return when (keycode) {
                Input.Keys.W -> PLAYER_UP_MOVE
                Input.Keys.S -> PLAYER_DOWN_MOVE
                Input.Keys.A -> PLAYER_LEFT_MOVE
                Input.Keys.D -> PLAYER_RIGHT_MOVE
                else -> null
            }
        }
    }
}
