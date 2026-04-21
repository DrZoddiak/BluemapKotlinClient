package com.github.drzoddiak.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.descriptors.buildClassSerialDescriptor
import kotlinx.serialization.descriptors.element
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder
import kotlinx.serialization.encoding.decodeStructure
import kotlinx.serialization.encoding.encodeStructure
import org.locationtech.jts.geom.Coordinate

object CoordinateSerializer : KSerializer<Coordinate> {
    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Coordinate") {
        element<Double>("x")
        element<Double>("y")
        element<Double>("z")
    }

    override fun deserialize(decoder: Decoder): Coordinate {
        return decoder.decodeStructure(descriptor) {
            var x = 0.0
            var y = 0.0
            var z = 0.0

            while (true) {
                when (decodeElementIndex(descriptor)) {
                    0 -> x = decodeDoubleElement(descriptor, 0)
                    1 -> y = decodeDoubleElement(descriptor, 1)
                    2 -> z = decodeDoubleElement(descriptor, 2)
                    else -> break
                }
            }
            Coordinate(x, y, z)
        }
    }

    override fun serialize(encoder: Encoder, value: Coordinate) {
        encoder.encodeStructure(descriptor) {
            encodeDoubleElement(descriptor, 0, value.x)
            encodeDoubleElement(descriptor, 1, value.y)
            encodeDoubleElement(descriptor, 2, value.z)
        }
    }
}