package com.github.drzoddiak.serializers

import kotlinx.serialization.KSerializer
import kotlinx.serialization.builtins.MapSerializer
import kotlinx.serialization.builtins.serializer
import kotlinx.serialization.descriptors.SerialDescriptor
import kotlinx.serialization.encoding.Decoder
import kotlinx.serialization.encoding.Encoder

abstract class StringMapSerializer<T>(serializer: KSerializer<T>) : KSerializer<Map<String, T>> {

    private val mapSerializer = MapSerializer(String.serializer(), serializer)

    override val descriptor: SerialDescriptor = mapSerializer.descriptor

    override fun deserialize(decoder: Decoder): Map<String, T> {
        return decoder.decodeSerializableValue(mapSerializer)
    }

    override fun serialize(encoder: Encoder, value: Map<String, T>) {
        encoder.encodeSerializableValue(mapSerializer, value)
    }
}