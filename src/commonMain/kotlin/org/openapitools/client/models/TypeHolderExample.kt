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


import kotlinx.serialization.*
import kotlinx.serialization.internal.CommonEnumSerializer
/**
 * 
 * @param stringItem 
 * @param numberItem 
 * @param floatItem 
 * @param integerItem 
 * @param boolItem 
 * @param arrayItem 
 */
@Serializable
data class TypeHolderExample (
    @SerialName(value = "string_item") @Required val stringItem: kotlin.String,
    @SerialName(value = "number_item") @Required val numberItem: kotlin.Double,
    @SerialName(value = "float_item") @Required val floatItem: kotlin.Float,
    @SerialName(value = "integer_item") @Required val integerItem: kotlin.Int,
    @SerialName(value = "bool_item") @Required val boolItem: kotlin.Boolean,
    @SerialName(value = "array_item") @Required val arrayItem: kotlin.Array<kotlin.Int>
) 



