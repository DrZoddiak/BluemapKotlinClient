package com.github.drzoddiak.model

import com.github.drzoddiak.serializers.CoordinateSerializer
import com.github.drzoddiak.serializers.PlaceMarkerSerializer
import com.github.drzoddiak.serializers.PointSerializer
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonArray
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.Point

object POISerializable {
    @Serializable
    data class PlaceOfInterest(
        val label: String,
        val toggleable: Boolean,
        val defaultHidden: Boolean,
        val sorting: Int,
        @Serializable(with = PlaceMarkerSerializer::class)
        val markers: Map<String, PlaceMarker>,
    )

    @Serializable
    data class PlaceMarker(
        val classes: JsonArray,
        val detail: String,
        val icon: String,
        @Serializable(with = PointSerializer::class)
        val anchor: Point,
        val minDistance: Double,
        val maxDistance: Double,
        val type: String,
        val label: String,
        @Serializable(with = CoordinateSerializer::class)
        val position: Coordinate,
        val sorting: Int,
        val listed: Boolean
    )
}