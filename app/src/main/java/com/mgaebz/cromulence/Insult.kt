package com.mgaebz.cromulence

data class Insult(
        val id: Int,
        val text: String = ShakesInsultGenerator.generate(),
        var stale: Boolean = false
)