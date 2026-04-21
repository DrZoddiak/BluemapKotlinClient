package com.github.drzoddiak.model

import com.github.drzoddiak.model.RegionSerializable.BridgeData

/**
 * Represents a bridge interface that facilitates interactions of various data.
 *
 * A default representation exists as BlueBridgeSerializable but can be substituted as needed.
 * Typically, in the case where you may need to consume alternatively named, or additional fields.
 *
 * @property trains The data associated with train locations.
 * @property busses The data associated with bus locations.
 * @property placesOfInterest The data representing locations of interest.
 * @property chunky Holds information about Chunky region data.
 * @property worldguardBridge Represents data pertaining to WorldGuard regions.
 * @property griefpreventionBridge Represents data relating to GriefPrevention claims.
 */
interface IBlueBridge {
    val trains: POISerializable.PlaceOfInterest?
    val busses : POISerializable.PlaceOfInterest?
    val placesOfInterest: POISerializable.PlaceOfInterest?
    val chunky: BridgeData?
    val worldguardBridge: BridgeData?
    val griefpreventionBridge: BridgeData?
}