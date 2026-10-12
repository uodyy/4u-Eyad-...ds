package com.checker.discord.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface DiscordAPI {
    @GET("unique-username/username-attempt?username={username}")
    suspend fun checkUsername(@Path("username") username: String): Response<Map<String, Any>>
}

