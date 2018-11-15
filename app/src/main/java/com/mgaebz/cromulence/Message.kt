package com.mgaebz.cromulence

data class Message(
        val id: Int,
        val text: String = ShakesInsultGenerator.generate(),
        var stale: Boolean = false
)