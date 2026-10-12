package com.checker.discord.util

import kotlin.random.Random

class UsernameGenerator(
    private val length: Int = 3,
    private val includeLetters: Boolean = true,
    private val includeNumbers: Boolean = false,
    private val includeDots: Boolean = false,
    private val includeUnderscores: Boolean = false
) {
    private val generated = mutableSetOf<String>()

    fun generate(count: Int): List<String> {
        val result = mutableListOf<String>()
        val maxAttempts = count * 10

        var attempts = 0
        while (result.size < count && attempts < maxAttempts) {
            val username = generateOne()
            if (username !in generated) {
                result.add(username)
                generated.add(username)
            }
            attempts++
        }

        return result
    }

    private fun generateOne(): String {
        val chars = mutableListOf<Char>()

        // Add letters
        if (includeLetters) {
            chars.addAll(('a'..'z').toList())
        }

        // Add numbers
        if (includeNumbers) {
            chars.addAll(('0'..'9').toList())
        }

        // Add special chars
        if (includeDots) {
            chars.add('.')
        }
        if (includeUnderscores) {
            chars.add('_')
        }

        if (chars.isEmpty()) {
            chars.addAll(('a'..'z').toList())
        }

        var result = ""
        val random = Random

        // Ensure it starts with a letter
        result += ('a'..'z').random()

        // Generate rest
        for (i in 1 until length) {
            result += chars.random()
        }

        // Ensure it doesn't start/end with special chars
        while (result.startsWith(".") || result.startsWith("_") ||
               result.endsWith(".") || result.endsWith("_")) {
            result = ('a'..'z').random().toString() + result.substring(1)
        }

        return result.lowercase()
    }

    fun clear() {
        generated.clear()
    }
}
