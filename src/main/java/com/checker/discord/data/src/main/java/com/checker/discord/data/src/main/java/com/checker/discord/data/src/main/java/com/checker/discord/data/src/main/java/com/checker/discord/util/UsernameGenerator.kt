
package com.checker.discord.util

import kotlin.random.Random

object UsernameGenerator {
    private val CHAR_POOL = "abcdefghijklmnopqrstuvwxyz0123456789"

    fun generateRandomUsername(length: Int = 6): String {
        return (1..length)
            .map { Random.nextInt(0, CHAR_POOL.length) }
            .map(CHAR_POOL::get)
            .joinToString("")
    }
}
