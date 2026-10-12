package com.checker.discord.util

object UsernameValidator {
    private val RESERVED_WORDS = setOf(
        "discord", "clyde", "wumpus", "everyone", "here", "admin", "moderator"
    )

    private val ALLOWED_CHARS = Regex("^[a-z0-9._-]+$")

    fun validate(username: String): ValidationResult {
        val clean = username.lowercase().trim()

        // Check length
        if (clean.length < 2) {
            return ValidationResult.Invalid("Username too short (minimum 2 characters)")
        }
        if (clean.length > 32) {
            return ValidationResult.Invalid("Username too long (maximum 32 characters)")
        }

        // Check characters
        if (!ALLOWED_CHARS.matches(clean)) {
            return ValidationResult.Invalid("Username contains invalid characters")
        }

        // Check for consecutive dots
        if (clean.contains("..")) {
            return ValidationResult.Invalid("Username cannot contain consecutive dots")
        }

        // Check start/end with dot or dash
        if (clean.startsWith(".") || clean.endsWith(".")) {
            return ValidationResult.Invalid("Username cannot start or end with a dot")
        }

        if (clean.startsWith("-") || clean.endsWith("-")) {
            return ValidationResult.Invalid("Username cannot start or end with a dash")
        }

        // Check for reserved words
        if (RESERVED_WORDS.any { clean.contains(it) }) {
            return ValidationResult.Invalid("Username contains reserved words")
        }

        // Check for numbers only
        if (clean.all { it.isDigit() }) {
            return ValidationResult.Invalid("Username cannot contain only numbers")
        }

        return ValidationResult.Valid
    }
}

sealed class ValidationResult {
    object Valid : ValidationResult()
    data class Invalid(val reason: String) : ValidationResult()
}
