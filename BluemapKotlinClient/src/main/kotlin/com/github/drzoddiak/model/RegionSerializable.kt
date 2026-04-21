package com.github.drzoddiak.model

import com.github.drzoddiak.serializers.CoordinateSerializer
import com.github.drzoddiak.serializers.PointSerializer
import com.github.drzoddiak.serializers.RegionMarkerSerializer
import kotlinx.serialization.Serializable
import org.locationtech.jts.geom.Coordinate
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point
import org.locationtech.jts.geom.Polygon

object RegionSerializable {
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
        @Serializable(with = RegionMarkerSerializer::class)
        val markers: Map<String, RegionMarker>,
    )

    private val factory
        get() = GeometryFactory()

    @Serializable
    data class RegionMarker(
        val shape: List<@Serializable(with = PointSerializer::class) Point>,
        val holes: List<@Serializable(with = PointSerializer::class) Point>,
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
        @Serializable(with = CoordinateSerializer::class)
        val position: Coordinate,
        val sorting: Int,
        val listed: Boolean
    ) {
        /**
         * Gives a polygon of the given points in the marker
         */
        fun polygon(): Polygon {
            return shape.toMutableList().also { list ->
                // This completes the 'ring'
                list.firstOrNull()?.let { entry ->
                    list.add(entry)
                }
            }.map {
                Coordinate(it.x, it.y)
            }.let {
                factory.createLinearRing(it.toTypedArray())
            }.let {
                factory.createPolygon(it)
            }
        }
    }

    @Serializable
    data class RGBA(
        val r: Int,
        val g: Int,
        val b: Int,
        val a: Double
    )
}
