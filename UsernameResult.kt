package com.checker.discord.data

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "username_results")
data class UsernameResult(
    @PrimaryKey
    val username: String,
    val status: String, // "available", "taken", "invalid", "uncertain"
    val reason: String? = null,
    val checkedAt: Long = System.currentTimeMillis(),
    val source: String = "discord"
)

sealed class CheckResult {
    data class Available(val username: String) : CheckResult()
    data class Taken(val username: String) : CheckResult()
    data class Invalid(val username: String, val reason: String) : CheckResult()
    data class Uncertain(val username: String, val error: String) : CheckResult()
}
