package org.openapitools.client.infrastructure

import kotlinx.serialization.*
import kotlinx.serialization.internal.StringDescriptor

@Serializable
class Base64ByteArray(val value: ByteArray) {
    @Serializer(Base64ByteArray::class)
    companion object : KSerializer<Base64ByteArray> {
        override val descriptor = StringDescriptor.withName("Base64ByteArray")
        override fun serialize(encoder: Encoder, obj: Base64ByteArray) = encoder.encodeString(obj.value.encodeBase64())
        override fun deserialize(decoder: Decoder) = Base64ByteArray(decoder.decodeString().decodeBase64Bytes())
    }
}