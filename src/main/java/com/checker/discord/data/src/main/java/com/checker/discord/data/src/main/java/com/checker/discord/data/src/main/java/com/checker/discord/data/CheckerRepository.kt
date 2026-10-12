package com.checker.discord.data

import com.checker.discord.network.DiscordAPI
import kotlinx.coroutines.flow.Flow

class CheckerRepository(private val api: DiscordAPI, private val dao: ResultDao) {
    val allResults: Flow<List<UsernameResult>> = dao.getAllResults()

    suspend fun checkUsername(username: String): Boolean {
        val response = api.checkUsername(username)
        val available = response.isSuccessful && (response.body()?.get("taken") == false)
        dao.insert(UsernameResult(username = username, isAvailable = available))
        return available
    }
}
