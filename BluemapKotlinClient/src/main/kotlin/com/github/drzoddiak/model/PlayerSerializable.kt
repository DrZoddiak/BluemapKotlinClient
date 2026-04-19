package com.github.drzoddiak.model

import kotlinx.serialization.Serializable

object PlayerSerializable {

    @Serializable
    data class PlayerData(
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
        val position: LocationSerializable.Position,
        // pitch/yaw/roll of the player
        val rotation: LocationSerializable.Rotation
    )

}