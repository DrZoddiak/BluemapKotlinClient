package com.github.drzoddiak.model

import com.github.drzoddiak.model.RegionSerializable.BridgeData
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class BlueBridgeSerializable(
    @SerialName("Trains")
    override val trains: POISerializable.PlaceOfInterest? = null,
    @SerialName("Busses")
    override val busses : POISerializable.PlaceOfInterest? = null,
    @SerialName("Places of Interest")
    override val placesOfInterest: POISerializable.PlaceOfInterest? = null,
    override val chunky: BridgeData? = null,
    @SerialName("BlueBridgeWG-0652e60a-dd04-4ec6-a803-be0c6caae1c8")
    override val worldguardBridge: BridgeData? = null,
    // Statecraft doesn't use GP, so I don't provide a serial name here
    override val griefpreventionBridge: BridgeData? = null,
) : IBlueBridge