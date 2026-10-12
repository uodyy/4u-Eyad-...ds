package com.checker.discord.data

import com.checker.discord.network.RetrofitClient
import com.google.gson.JsonObject
import kotlinx.coroutines.delay

class CheckerRepository(private val dao: ResultDao) {
    private val api = RetrofitClient.discordAPI

    suspend fun checkUsername(username: String): CheckResult {
        val cached = dao.getByUsername(username)
        if (cached != null) {
            return mapToCheckResult(cached)
        }

        return try {
            val request = JsonObject().apply {
                addProperty("username", username)
            }

            val response = api.checkUsername(request)

            if (!response.isSuccessful) {
                val result = UsernameResult(
                    username = username,
                    status = "uncertain",
                    reason = "Network error: ${response.code()}"
                )
                dao.insert(result)
                return CheckResult.Uncertain(username, "Network error: ${response.code()}")
            }

            val body = response.body() ?: run {
                val result = UsernameResult(
                    username = username,
                    status = "uncertain",
                    reason = "Empty response"
                )
                dao.insert(result)
                return CheckResult.Uncertain(username, "Empty response")
            }

            val status = when {
                body.available == true -> "available"
                body.taken == true -> "taken"
                body.reason != null -> "invalid"
                else -> "uncertain"
            }

            val result = UsernameResult(
                username = username,
                status = status,
                reason = body.reasonMessage ?: body.reason
            )

            dao.insert(result)

            mapToCheckResult(result)
        } catch (e: Exception) {
            val result = UsernameResult(
                username = username,
                status = "uncertain",
                reason = "Error: ${e.message}"
            )
            dao.insert(result)
            CheckResult.Uncertain(username, e.message ?: "Unknown error")
        }
    }

    suspend fun checkMultiple(usernames: List<String>, delayMs: Long = 500): List<CheckResult> {
        val results = mutableListOf<CheckResult>()
        for (username in usernames) {
            results.add(checkUsername(username))
            delay(delayMs)
        }
        return results
    }

    fun getResultsFlow() = dao.getAll()

    fun getResultsByStatusFlow(status: String) = dao.getByStatus(status)

    suspend fun getAvailableCount() = dao.getCountByStatus("available")

    suspend fun getTakenCount() = dao.getCountByStatus("taken")

    suspend fun getInvalidCount() = dao.getCountByStatus("invalid")

    suspend fun getUncertainCount() = dao.getCountByStatus("uncertain")

    suspend fun getTotalCount() = dao.getCount()

    suspend fun clearAll() = dao.deleteAll()

    suspend fun deleteResult(username: String) {
        dao.getByUsername(username)?.let { dao.delete(it) }
    }

    private fun mapToCheckResult(result: UsernameResult): CheckResult {
        return when (result.status) {
            "available" -> CheckResult.Available(result.username)
            "taken" -> CheckResult.Taken(result.username)
            "invalid" -> CheckResult.Invalid(result.username, result.reason ?: "Invalid")
            else -> CheckResult.Uncertain(result.username, result.reason ?: "Unknown")
        }
    }
}
