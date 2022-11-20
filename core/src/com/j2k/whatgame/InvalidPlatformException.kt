package com.j2k.whatgame

import java.lang.Exception

class InvalidPlatformException : Exception {
    constructor() : super()
    constructor(message: String) : super(message)
}
