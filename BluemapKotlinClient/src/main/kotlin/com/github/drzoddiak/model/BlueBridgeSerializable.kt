package com.github.drzoddiak.model

import com.github.drzoddiak.model.RegionSerializable.BridgeData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlueBridgeSerializable(
    @SerialName("Trains")
    val trains: POISerializable.PlaceOfInterest? = null,
    @SerialName("Busses")
    val busses : POISerializable.PlaceOfInterest? = null,
    @SerialName("Places of Interest")
    val placesOfInterest: POISerializable.PlaceOfInterest? = null,
    val chunky: BridgeData? = null,
    @SerialName("BlueBridgeWG-0652e60a-dd04-4ec6-a803-be0c6caae1c8")
    val worldguardBridge: BridgeData? = null,
)