package com.github.drzoddiak.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonObject
import kotlinx.serialization.json.decodeFromJsonElement

object BluemapSerializableData {
    @Serializable
    data class Position(
        val x: Double,
        val y: Double,
        val z: Double
    ) {
        override fun toString(): String {
            return "$x,$y,$z"
        }
    }

    @Serializable
    data class RegionCoordinates(
        val x: Double,
        val z: Double,
    ) {
        override fun toString(): String {
            return "$x,$z"
        }
    }

    @Serializable
    data class RGBA(
        val r: Int,
        val g: Int,
        val b: Int,
        val a: Double
    )

    @Serializable
    data class Marker(
        val shape: List<RegionCoordinates>,
        val holes: List<JsonObject>,
        val shapeY: Double,
        val depthTest: Boolean,
        val lineWidth: Int,
        val lineColor: RGBA,
        val fillColor: RGBA,
        val detail: String,
        val newTab: Boolean,
        val minDistance: Double,
        val maxDistance: Double,
        val type: String,
        val label: String,
        val position: Position,
        val sorting: Int,
        val listed: Boolean
    )

    @Serializable
    data class BridgeData(
        // "Worldguard Regions"
        val label: String,
        // True
        val toggleable: Boolean,
        // True
        val defaultHidden: Boolean,
        // 0
        val sorting: Int,
        // Collection of (But not a list) worldguard regions
        // We know the sub-object of this object, but because there is an unknowable number of, and name of at compile
        // the name of the markers we have to sacrifice consistency
        @SerialName("markers")
        val data: JsonObject,
    ) {
        @SerialName("")
        val markers: List<Marker> = data.values.map(Json::decodeFromJsonElement)
    }

    @Serializable
    data class BlueBridge(
        @SerialName("Trains")
        val trains: JsonObject? = null,
        @SerialName("Places Of Interest")
        val placesOfInterest: JsonObject? = null,
        val chunky: JsonObject? = null,
        @SerialName("BlueBridgeWG-0652e60a-dd04-4ec6-a803-be0c6caae1c8")
        val worldguardBridge: BridgeData
    )

    data class Region(
        val label: String,
        val owner: String,
        val members: String,
    ) {

        override fun toString(): String {
            return """
                 Label : $label | Owner: $owner | Members : $members
                """.trimIndent()
        }
    }
}
