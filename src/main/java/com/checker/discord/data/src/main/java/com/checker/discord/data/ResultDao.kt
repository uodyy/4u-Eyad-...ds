package com.checker.discord.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultDao {
    @Insert
    suspend fun insert(result: UsernameResult)

    @Query("SELECT * FROM checked_usernames ORDER BY id DESC")
    fun getAllResults(): Flow<List<UsernameResult>>
}
