package com.checker.discord.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "checked_usernames")
data class UsernameResult(
    @PrimaryKey(autoGenerate = true) val id: Long = 0,
    val username: String,
    val isAvailable: Boolean,
    val timestamp: Long = System.currentTimeMillis()
)
