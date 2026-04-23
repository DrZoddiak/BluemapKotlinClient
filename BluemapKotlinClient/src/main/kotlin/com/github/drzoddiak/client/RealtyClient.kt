package com.github.drzoddiak.client

import com.github.drzoddiak.model.PlayerSerializable
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.UserAgent
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.request
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RealtyClient(val url: String = "https://map.mcstatecraft.com/maps/newhamilton/live") {

    private val client: HttpClient = HttpClient(CIO) {
        headers {
            append("Accept", "application/json")
        }
        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
        install(UserAgent) {
            agent = "BluemapKotlinClient"
        }
    }

    fun closeClient() {
        client.close()
    }

    suspend fun <R> requestRaw(execute: suspend (HttpClient) -> R): R {
        return execute.invoke(client)
    }

    suspend inline fun <reified T, R> requestRegionData(crossinline execute: suspend (T) -> R): R {
        return request<T, R>("markers.json") { execute(it) }
    }

    @JvmName("requestRegionDataUnit")
    suspend inline fun <reified T> requestRegionData(crossinline execute: suspend (T) -> Unit) {
        return request<T, Unit>("markers.json") { execute(it) }
    }

    suspend fun <R> requestPlayerData(execute: suspend (PlayerSerializable.Players) -> R): R {
        return request<PlayerSerializable.Players, R>("players.json") { execute(it) }
    }

    suspend inline fun <reified T, R> request(urlAppend: String, crossinline execute: suspend (T) -> R): R {
        return requestRaw { client ->
            client.get("$url/$urlAppend").request.call.body<T>()
                .let { execute(it) }
        }
    }
}