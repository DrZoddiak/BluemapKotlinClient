package com.github.drzoddiak.model

import com.github.drzoddiak.serializers.PointSerializer
import kotlinx.serialization.Serializable
import org.locationtech.jts.geom.Point

object PlayerSerializable {
    @Serializable
    data class Players(
        val players: List<Player>
    )

    @Serializable
    data class Player(
        // Players In-game UUID
        val uuid: String,
        // Players username
        val name: String,
        // If the player is in a foreign world (nether/outback/etc..)
        val foreign: Boolean,
        // x,y,z position
        @Serializable(with = PointSerializer::class)
        val position: Point,
        // pitch/yaw/roll of the player
        val rotation: Rotation
    )

    @Serializable
    data class Rotation(
        val pitch: Double,
        val yaw: Double,
        val roll: Double
    )
}