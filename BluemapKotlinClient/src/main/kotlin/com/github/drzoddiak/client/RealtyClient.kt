package com.github.drzoddiak.client

import io.ktor.client.HttpClient
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RealtyClient(private val url: String = "https://map.mcstatecraft.com/maps/newhamilton/live/markers.json") {
    suspend fun realtyClient(execute: suspend (HttpResponse) -> Unit) {
        val client = HttpClient(CIO) {
            headers {
                append("Accept", "application/json")
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
        val response = client.get(url)
        execute(response)
        client.close()
    }
}