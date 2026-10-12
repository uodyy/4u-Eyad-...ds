package com.checker.discord.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Delete
import kotlinx.coroutines.flow.Flow

@Dao
interface ResultDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(result: UsernameResult)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(results: List<UsernameResult>)

    @Query("SELECT * FROM username_results WHERE username = :username")
    suspend fun getByUsername(username: String): UsernameResult?

    @Query("SELECT * FROM username_results WHERE status = :status ORDER BY checkedAt DESC")
    fun getByStatus(status: String): Flow<List<UsernameResult>>

    @Query("SELECT * FROM username_results ORDER BY checkedAt DESC")
    fun getAll(): Flow<List<UsernameResult>>

    @Query("SELECT COUNT(*) FROM username_results")
    suspend fun getCount(): Int

    @Query("SELECT COUNT(*) FROM username_results WHERE status = :status")
    suspend fun getCountByStatus(status: String): Int

    @Query("SELECT * FROM username_results WHERE status = :status LIMIT 1")
    suspend fun getFirstByStatus(status: String): UsernameResult?

    @Delete
    suspend fun delete(result: UsernameResult)

    @Query("DELETE FROM username_results")
    suspend fun deleteAll()

    @Query("DELETE FROM username_results WHERE username IN (:usernames)")
    suspend fun deleteByUsernames(usernames: List<String>)

    @Query("SELECT EXISTS(SELECT 1 FROM username_results WHERE username = :username)")
    suspend fun exists(username: String): Boolean
}
