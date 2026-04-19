package com.github.drzoddiak.model

import kotlinx.serialization.Serializable

object LocationSerializable {

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
    data class AnchorCoordinate(
        val x: Double,
        val y: Double
    )

    @Serializable
    data class Rotation(
        val pitch: Double,
        val yaw: Double,
        val roll: Double
    )
}