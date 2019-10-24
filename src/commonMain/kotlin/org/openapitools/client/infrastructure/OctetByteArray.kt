package org.openapitools.client.infrastructure

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

@Serializable
class OctetByteArray(val value: ByteArray) {
    @Serializer(OctetByteArray::class)
    companion object : KSerializer<OctetByteArray> {
        override val descriptor = StringDescriptor.withName("OctetByteArray")
        override fun serialize(encoder: Encoder, obj: OctetByteArray) = encoder.encodeString(hex(obj.value))
        override fun deserialize(decoder: Decoder) = OctetByteArray(hex(decoder.decodeString()))
    }
}