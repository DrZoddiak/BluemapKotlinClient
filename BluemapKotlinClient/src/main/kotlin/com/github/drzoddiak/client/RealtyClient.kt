package com.github.drzoddiak.client

import com.github.drzoddiak.model.BluemapSerializableData
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.cio.CIO
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.request
import io.ktor.http.headers
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class RealtyClient(private val url: String = "https://map.mcstatecraft.com/maps/newhamilton/live/markers.json") {
    suspend fun request(execute: suspend (BluemapSerializableData.BlueBridge) -> Unit) {
        val client = HttpClient(CIO) {
            headers {
                append("Accept", "application/json")
            }
            install(ContentNegotiation) {
                json(Json { ignoreUnknownKeys = true })
            }
        }
        val response = client.get(url).realtyData()
        execute(response)
        client.close()
    }

    private suspend fun HttpResponse.realtyData(): BluemapSerializableData.BlueBridge {
        return request.call.body()
    }
}