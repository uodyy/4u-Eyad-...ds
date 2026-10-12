package com.checker.discord.network

import retrofit2.http.POST
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.Response
import com.google.gson.JsonObject

interface DiscordAPI {
    @POST("api/v9/unique-username/username-attempt-unauthed")
    suspend fun checkUsername(
        @Body request: JsonObject,
        @Header("Content-Type") contentType: String = "application/json",
        @Header("User-Agent") userAgent: String = "Mozilla/5.0"
    ): Response<UsernameCheckResponse>
}

data class UsernameCheckResponse(
    val username: String? = null,
    val taken: Boolean? = null,
    val available: Boolean? = null,
    val status: Int? = null,
    val reason: String? = null,
    val reasonMessage: String? = null,
    val inputUsername: String? = null,
    val checkedAt: String? = null,
    val source: String? = null
)
