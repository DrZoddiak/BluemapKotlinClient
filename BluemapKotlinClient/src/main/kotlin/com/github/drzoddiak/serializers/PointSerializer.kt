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
import org.locationtech.jts.geom.GeometryFactory
import org.locationtech.jts.geom.Point

object PointSerializer : KSerializer<Point> {
    private val factory
        get() = GeometryFactory()

    override val descriptor: SerialDescriptor = buildClassSerialDescriptor("Point") {
        element<Double>("x")
        element<Double>("y", isOptional = true)
        element<Double>("z", isOptional = true)
    }

    override fun deserialize(decoder: Decoder): Point {
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
            factory.createPoint(Coordinate(x, z))
        }
    }

    override fun serialize(encoder: Encoder, value: Point) {
        encoder.encodeStructure(descriptor) {
            encodeDoubleElement(descriptor, 0, value.x)
            encodeDoubleElement(descriptor, 1, value.y)
            encodeDoubleElement(descriptor, 2, value.coordinate.z)
        }
    }
}