package com.checker.discord.util

object UsernameValidator {
    fun isValid(username: String): Boolean {
        if (username.length < 2 || username.length > 32) return false
        val regex = "^[a-zA-Z0-9_.]+$".toRegex()
        return username.matches(regex)
    }
}
