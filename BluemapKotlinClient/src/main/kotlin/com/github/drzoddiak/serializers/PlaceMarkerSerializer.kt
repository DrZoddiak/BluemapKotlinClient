package com.github.drzoddiak.serializers

import com.github.drzoddiak.model.POISerializable

object PlaceMarkerSerializer : StringMapSerializer<POISerializable.PlaceMarker>(POISerializable.PlaceMarker.serializer())

