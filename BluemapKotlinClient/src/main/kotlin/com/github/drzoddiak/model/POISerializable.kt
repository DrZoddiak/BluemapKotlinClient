package com.github.drzoddiak.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonArray
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

object POISerializable {
    @Serializable
    data class PlaceOfInterest(
        val label: String,
        val toggleable: Boolean,
        val defaultHidden: Boolean,
        val sorting: Int,
        @SerialName("markers")
        val data: JsonObject,
    ) {
        @SerialName("")
        val markers: List<PlaceMarker> = data.values.map(Json::decodeFromJsonElement)
    }

    @Serializable
    data class PlaceMarker(
        val classes: JsonArray,
        val detail: String,
        val icon: String,
        val anchor: LocationSerializable.AnchorCoordinate,
        val minDistance: Double,
        val maxDistance: Double,
        val type: String,
        val label: String,
        val position: LocationSerializable.Position,
        val sorting: Int,
        val listed: Boolean
    )
}