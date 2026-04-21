package com.github.drzoddiak.serializers

import com.github.drzoddiak.model.RegionSerializable

object RegionMarkerSerializer : StringMapSerializer<RegionSerializable.RegionMarker>(RegionSerializable.RegionMarker.serializer())