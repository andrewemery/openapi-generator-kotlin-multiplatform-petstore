/**
* OpenAPI Petstore
* This spec is mainly for testing Petstore server and contains fake endpoints, models. Please do not use this for any other purpose. Special characters: \" \\
*
* The version of the OpenAPI document: 1.0.0
* 
*
* NOTE: This class is auto generated by OpenAPI Generator (https://openapi-generator.tech).
* https://openapi-generator.tech
* Do not edit the class manually.
*/
package org.openapitools.client.models

import org.openapitools.client.models.OuterEnum

import kotlinx.serialization.*
import kotlinx.serialization.internal.CommonEnumSerializer
/**
 * 
 * @param enumString 
 * @param enumStringRequired 
 * @param enumInteger 
 * @param enumNumber 
 * @param outerEnum 
 */
@Serializable
data class EnumTest (
    @SerialName(value = "enum_string_required") @Required val enumStringRequired: EnumTest.EnumStringRequired,
    @SerialName(value = "enum_string") val enumString: EnumTest.EnumString? = null,
    @SerialName(value = "enum_integer") val enumInteger: EnumTest.EnumInteger? = null,
    @SerialName(value = "enum_number") val enumNumber: EnumTest.EnumNumber? = null,
    @SerialName(value = "outerEnum") val outerEnum: OuterEnum? = null
) 


{
    /**
    * 
    * Values: uPPER,lower,eMPTY
    */
    @Serializable(with = EnumString.Serializer::class)
    enum class EnumString(val value: kotlin.String){
        uPPER("UPPER"),
        lower("lower"),
        eMPTY("");

        object Serializer : CommonEnumSerializer<EnumString>("EnumString", values(), values().map { it.value.toString() }.toTypedArray())
    }
    /**
    * 
    * Values: uPPER,lower,eMPTY
    */
    @Serializable(with = EnumStringRequired.Serializer::class)
    enum class EnumStringRequired(val value: kotlin.String){
        uPPER("UPPER"),
        lower("lower"),
        eMPTY("");

        object Serializer : CommonEnumSerializer<EnumStringRequired>("EnumStringRequired", values(), values().map { it.value.toString() }.toTypedArray())
    }
    /**
    * 
    * Values: _1,minus1
    */
    @Serializable(with = EnumInteger.Serializer::class)
    enum class EnumInteger(val value: kotlin.Int){
        _1(1),
        minus1(-1);

        object Serializer : CommonEnumSerializer<EnumInteger>("EnumInteger", values(), values().map { it.value.toString() }.toTypedArray())
    }
    /**
    * 
    * Values: _1period1,minus1Period2
    */
    @Serializable(with = EnumNumber.Serializer::class)
    enum class EnumNumber(val value: kotlin.Double){
        _1period1(1.1),
        minus1Period2(-1.2);

        object Serializer : CommonEnumSerializer<EnumNumber>("EnumNumber", values(), values().map { it.value.toString() }.toTypedArray())
    }
}

