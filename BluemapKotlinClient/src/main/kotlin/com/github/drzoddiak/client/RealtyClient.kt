package com.github.drzoddiak.client

import com.github.drzoddiak.model.BlueBridgeSerializable
import com.github.drzoddiak.model.PlayerSerializable
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.request
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RealtyClient(val url: String = "https://map.mcstatecraft.com/maps/newhamilton/live") {

    suspend fun requestRaw(execute: suspend (HttpClient) -> Unit) {
        val client = HttpClient(CIO) {
            headers {
                append("Accept", "application/json")
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
        execute.invoke(client)
        client.close()
    }

    suspend fun requestRegionData(execute: suspend (BlueBridgeSerializable) -> Unit) {
        request<BlueBridgeSerializable>("markers.json") { execute(it) }
    }

    suspend fun requestPlayerData(execute: suspend (PlayerSerializable.PlayerData) -> Unit) {
        request<PlayerSerializable.PlayerData>("players.json") { execute(it) }
    }

    suspend inline fun <reified T> request(urlAppend: String, crossinline execute: suspend (T) -> Unit) {
        requestRaw { client ->
            client.get("$url/$urlAppend").request.call.body<T>()
                .let { execute(it) }
        }
    }
}