# FakeApi

All URIs are relative to *http://petstore.swagger.io:80/v2*

Method | HTTP request | Description
------------- | ------------- | -------------
[**createXmlItem**](FakeApi.md#createXmlItem) | **POST** /fake/create_xml_item | creates an XmlItem
[**fakeOuterBooleanSerialize**](FakeApi.md#fakeOuterBooleanSerialize) | **POST** /fake/outer/boolean | 
[**fakeOuterCompositeSerialize**](FakeApi.md#fakeOuterCompositeSerialize) | **POST** /fake/outer/composite | 
[**fakeOuterNumberSerialize**](FakeApi.md#fakeOuterNumberSerialize) | **POST** /fake/outer/number | 
[**fakeOuterStringSerialize**](FakeApi.md#fakeOuterStringSerialize) | **POST** /fake/outer/string | 
[**testBodyWithFileSchema**](FakeApi.md#testBodyWithFileSchema) | **PUT** /fake/body-with-file-schema | 
[**testBodyWithQueryParams**](FakeApi.md#testBodyWithQueryParams) | **PUT** /fake/body-with-query-params | 
[**testClientModel**](FakeApi.md#testClientModel) | **PATCH** /fake | To test \&quot;client\&quot; model
[**testEndpointParameters**](FakeApi.md#testEndpointParameters) | **POST** /fake | Fake endpoint for testing various parameters 假端點 偽のエンドポイント 가짜 엔드 포인트 
[**testEnumData**](FakeApi.md#testEnumData) | **GET** /fake/enum-test | test json serialization of enum data
[**testEnumParameters**](FakeApi.md#testEnumParameters) | **GET** /fake | To test enum parameters
[**testFormats**](FakeApi.md#testFormats) | **GET** /fake/format-test | test json serialization of various formats
[**testGroupParameters**](FakeApi.md#testGroupParameters) | **DELETE** /fake | Fake endpoint to test group parameters (optional)
[**testInlineAdditionalProperties**](FakeApi.md#testInlineAdditionalProperties) | **POST** /fake/inline-additionalProperties | test inline additionalProperties
[**testJsonFormData**](FakeApi.md#testJsonFormData) | **GET** /fake/jsonFormData | test json serialization of form data
[**testQueryParameterCollectionFormat**](FakeApi.md#testQueryParameterCollectionFormat) | **PUT** /fake/test-query-paramters | 


<a name="createXmlItem"></a>
# **createXmlItem**
> createXmlItem(xmlItem)

creates an XmlItem

this route creates an XmlItem

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
val xmlItem : XmlItem =  // XmlItem | XmlItem Body
try {
    apiInstance.createXmlItem(xmlItem)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#createXmlItem")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#createXmlItem")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **xmlItem** | [**XmlItem**](XmlItem.md)| XmlItem Body |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/xml, application/xml; charset=utf-8, application/xml; charset=utf-16, text/xml, text/xml; charset=utf-8, text/xml; charset=utf-16
 - **Accept**: Not defined

<a name="fakeOuterBooleanSerialize"></a>
# **fakeOuterBooleanSerialize**
> kotlin.Boolean fakeOuterBooleanSerialize(body)



Test serialization of outer boolean types

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
val body : kotlin.Boolean = true // kotlin.Boolean | Input boolean as post body
try {
    val result : kotlin.Boolean = apiInstance.fakeOuterBooleanSerialize(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#fakeOuterBooleanSerialize")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#fakeOuterBooleanSerialize")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | **kotlin.Boolean**| Input boolean as post body | [optional]

### Return type

**kotlin.Boolean**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="fakeOuterCompositeSerialize"></a>
# **fakeOuterCompositeSerialize**
> OuterComposite fakeOuterCompositeSerialize(body)



Test serialization of object with outer number type

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
val body : OuterComposite =  // OuterComposite | Input composite as post body
try {
    val result : OuterComposite = apiInstance.fakeOuterCompositeSerialize(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#fakeOuterCompositeSerialize")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#fakeOuterCompositeSerialize")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**OuterComposite**](OuterComposite.md)| Input composite as post body | [optional]

### Return type

[**OuterComposite**](OuterComposite.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="fakeOuterNumberSerialize"></a>
# **fakeOuterNumberSerialize**
> kotlin.Double fakeOuterNumberSerialize(body)



Test serialization of outer number types

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
val body : kotlin.Double = 8.14 // kotlin.Double | Input number as post body
try {
    val result : kotlin.Double = apiInstance.fakeOuterNumberSerialize(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#fakeOuterNumberSerialize")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#fakeOuterNumberSerialize")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | **kotlin.Double**| Input number as post body | [optional]

### Return type

**kotlin.Double**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="fakeOuterStringSerialize"></a>
# **fakeOuterStringSerialize**
> kotlin.String fakeOuterStringSerialize(body)



Test serialization of outer string types

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
val body : kotlin.String = body_example // kotlin.String | Input string as post body
try {
    val result : kotlin.String = apiInstance.fakeOuterStringSerialize(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#fakeOuterStringSerialize")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#fakeOuterStringSerialize")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | **kotlin.String**| Input string as post body | [optional]

### Return type

**kotlin.String**

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: */*

<a name="testBodyWithFileSchema"></a>
# **testBodyWithFileSchema**
> testBodyWithFileSchema(body)



For this test, the body for this request much reference a schema named &#x60;File&#x60;.

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
val body : FileSchemaTestClass =  // FileSchemaTestClass | 
try {
    apiInstance.testBodyWithFileSchema(body)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#testBodyWithFileSchema")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#testBodyWithFileSchema")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**FileSchemaTestClass**](FileSchemaTestClass.md)|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="testBodyWithQueryParams"></a>
# **testBodyWithQueryParams**
> testBodyWithQueryParams(query, body)



### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
val query : kotlin.String = query_example // kotlin.String | 
val body : User =  // User | 
try {
    apiInstance.testBodyWithQueryParams(query, body)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#testBodyWithQueryParams")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#testBodyWithQueryParams")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **query** | **kotlin.String**|  |
 **body** | [**User**](User.md)|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="testClientModel"></a>
# **testClientModel**
> Client testClientModel(body)

To test \&quot;client\&quot; model

To test \&quot;client\&quot; model

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
val body : Client =  // Client | client model
try {
    val result : Client = apiInstance.testClientModel(body)
    println(result)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#testClientModel")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#testClientModel")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **body** | [**Client**](Client.md)| client model |

### Return type

[**Client**](Client.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: application/json

<a name="testEndpointParameters"></a>
# **testEndpointParameters**
> testEndpointParameters(number, double, patternWithoutDelimiter, byte, integer, int32, int64, float, string, binary, date, dateTime, password, paramCallback)

Fake endpoint for testing various parameters 假端點 偽のエンドポイント 가짜 엔드 포인트 

Fake endpoint for testing various parameters 假端點 偽のエンドポイント 가짜 엔드 포인트 

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
val number : kotlin.Double = 8.14 // kotlin.Double | None
val double : kotlin.Double = 1.2 // kotlin.Double | None
val patternWithoutDelimiter : kotlin.String = patternWithoutDelimiter_example // kotlin.String | None
val byte : org.openapitools.client.infrastructure.Base64ByteArray = BYTE_ARRAY_DATA_HERE // org.openapitools.client.infrastructure.Base64ByteArray | None
val integer : kotlin.Int = 56 // kotlin.Int | None
val int32 : kotlin.Int = 56 // kotlin.Int | None
val int64 : kotlin.Long = 789 // kotlin.Long | None
val float : kotlin.Float = 3.4 // kotlin.Float | None
val string : kotlin.String = string_example // kotlin.String | None
val binary : io.ktor.client.request.forms.InputProvider = BINARY_DATA_HERE // io.ktor.client.request.forms.InputProvider | None
val date : kotlin.String = 2013-10-20 // kotlin.String | None
val dateTime : kotlin.String = 2013-10-20T19:20:30+01:00 // kotlin.String | None
val password : kotlin.String = password_example // kotlin.String | None
val paramCallback : kotlin.String = paramCallback_example // kotlin.String | None
try {
    apiInstance.testEndpointParameters(number, double, patternWithoutDelimiter, byte, integer, int32, int64, float, string, binary, date, dateTime, password, paramCallback)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#testEndpointParameters")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#testEndpointParameters")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **number** | **kotlin.Double**| None |
 **double** | **kotlin.Double**| None |
 **patternWithoutDelimiter** | **kotlin.String**| None |
 **byte** | **org.openapitools.client.infrastructure.Base64ByteArray**| None |
 **integer** | **kotlin.Int**| None | [optional]
 **int32** | **kotlin.Int**| None | [optional]
 **int64** | **kotlin.Long**| None | [optional]
 **float** | **kotlin.Float**| None | [optional]
 **string** | **kotlin.String**| None | [optional]
 **binary** | **io.ktor.client.request.forms.InputProvider**| None | [optional]
 **date** | **kotlin.String**| None | [optional]
 **dateTime** | **kotlin.String**| None | [optional]
 **password** | **kotlin.String**| None | [optional]
 **paramCallback** | **kotlin.String**| None | [optional]

### Return type

null (empty response body)

### Authorization


Configure http_basic_test:
    ApiClient.username = ""
    ApiClient.password = ""

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: Not defined

<a name="testEnumData"></a>
# **testEnumData**
> EnumTest testEnumData()

test json serialization of enum data

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
try {
    val result : EnumTest = apiInstance.testEnumData()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#testEnumData")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#testEnumData")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**EnumTest**](EnumTest.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="testEnumParameters"></a>
# **testEnumParameters**
> testEnumParameters(enumHeaderStringArray, enumHeaderString, enumQueryStringArray, enumQueryString, enumQueryInteger, enumQueryDouble, enumFormStringArray, enumFormString)

To test enum parameters

To test enum parameters

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
val enumHeaderStringArray : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | Header parameter enum test (string array)
val enumHeaderString : kotlin.String = enumHeaderString_example // kotlin.String | Header parameter enum test (string)
val enumQueryStringArray : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | Query parameter enum test (string array)
val enumQueryString : kotlin.String = enumQueryString_example // kotlin.String | Query parameter enum test (string)
val enumQueryInteger : kotlin.Int = 56 // kotlin.Int | Query parameter enum test (double)
val enumQueryDouble : kotlin.Double = 1.2 // kotlin.Double | Query parameter enum test (double)
val enumFormStringArray : kotlin.Array<kotlin.String> = enumFormStringArray_example // kotlin.Array<kotlin.String> | Form parameter enum test (string array)
val enumFormString : kotlin.String = enumFormString_example // kotlin.String | Form parameter enum test (string)
try {
    apiInstance.testEnumParameters(enumHeaderStringArray, enumHeaderString, enumQueryStringArray, enumQueryString, enumQueryInteger, enumQueryDouble, enumFormStringArray, enumFormString)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#testEnumParameters")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#testEnumParameters")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **enumHeaderStringArray** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)| Header parameter enum test (string array) | [optional] [enum: >, $]
 **enumHeaderString** | **kotlin.String**| Header parameter enum test (string) | [optional] [default to &#39;-efg&#39;] [enum: _abc, -efg, (xyz)]
 **enumQueryStringArray** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)| Query parameter enum test (string array) | [optional] [enum: >, $]
 **enumQueryString** | **kotlin.String**| Query parameter enum test (string) | [optional] [default to &#39;-efg&#39;] [enum: _abc, -efg, (xyz)]
 **enumQueryInteger** | **kotlin.Int**| Query parameter enum test (double) | [optional] [enum: 1, -2]
 **enumQueryDouble** | **kotlin.Double**| Query parameter enum test (double) | [optional] [enum: 1.1, -1.2]
 **enumFormStringArray** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)| Form parameter enum test (string array) | [optional] [default to &#39;$&#39;] [enum: >, $]
 **enumFormString** | **kotlin.String**| Form parameter enum test (string) | [optional] [default to &#39;-efg&#39;] [enum: _abc, -efg, (xyz)]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: Not defined

<a name="testFormats"></a>
# **testFormats**
> FormatTest testFormats()

test json serialization of various formats

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
try {
    val result : FormatTest = apiInstance.testFormats()
    println(result)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#testFormats")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#testFormats")
    e.printStackTrace()
}
```

### Parameters
This endpoint does not need any parameter.

### Return type

[**FormatTest**](FormatTest.md)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: application/json

<a name="testGroupParameters"></a>
# **testGroupParameters**
> testGroupParameters(requiredStringGroup, requiredBooleanGroup, requiredInt64Group, stringGroup, booleanGroup, int64Group)

Fake endpoint to test group parameters (optional)

Fake endpoint to test group parameters (optional)

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
val requiredStringGroup : kotlin.Int = 56 // kotlin.Int | Required String in group parameters
val requiredBooleanGroup : kotlin.Boolean = true // kotlin.Boolean | Required Boolean in group parameters
val requiredInt64Group : kotlin.Long = 789 // kotlin.Long | Required Integer in group parameters
val stringGroup : kotlin.Int = 56 // kotlin.Int | String in group parameters
val booleanGroup : kotlin.Boolean = true // kotlin.Boolean | Boolean in group parameters
val int64Group : kotlin.Long = 789 // kotlin.Long | Integer in group parameters
try {
    apiInstance.testGroupParameters(requiredStringGroup, requiredBooleanGroup, requiredInt64Group, stringGroup, booleanGroup, int64Group)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#testGroupParameters")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#testGroupParameters")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **requiredStringGroup** | **kotlin.Int**| Required String in group parameters |
 **requiredBooleanGroup** | **kotlin.Boolean**| Required Boolean in group parameters |
 **requiredInt64Group** | **kotlin.Long**| Required Integer in group parameters |
 **stringGroup** | **kotlin.Int**| String in group parameters | [optional]
 **booleanGroup** | **kotlin.Boolean**| Boolean in group parameters | [optional]
 **int64Group** | **kotlin.Long**| Integer in group parameters | [optional]

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

<a name="testInlineAdditionalProperties"></a>
# **testInlineAdditionalProperties**
> testInlineAdditionalProperties(param)

test inline additionalProperties

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
val param : kotlin.collections.Map<kotlin.String, kotlin.String> =  // kotlin.collections.Map<kotlin.String, kotlin.String> | request body
try {
    apiInstance.testInlineAdditionalProperties(param)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#testInlineAdditionalProperties")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#testInlineAdditionalProperties")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **param** | [**kotlin.collections.Map&lt;kotlin.String, kotlin.String&gt;**](kotlin.String.md)| request body |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/json
 - **Accept**: Not defined

<a name="testJsonFormData"></a>
# **testJsonFormData**
> testJsonFormData(param, param2)

test json serialization of form data

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
val param : kotlin.String = param_example // kotlin.String | field1
val param2 : kotlin.String = param2_example // kotlin.String | field2
try {
    apiInstance.testJsonFormData(param, param2)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#testJsonFormData")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#testJsonFormData")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **param** | **kotlin.String**| field1 |
 **param2** | **kotlin.String**| field2 |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: application/x-www-form-urlencoded
 - **Accept**: Not defined

<a name="testQueryParameterCollectionFormat"></a>
# **testQueryParameterCollectionFormat**
> testQueryParameterCollectionFormat(pipe, ioutil, http, url, context)



To test the collection format in query parameters

### Example
```kotlin
// Import classes:
//import org.openapitools.client.infrastructure.*
//import org.openapitools.client.models.*

val apiInstance = FakeApi()
val pipe : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | 
val ioutil : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | 
val http : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | 
val url : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | 
val context : kotlin.Array<kotlin.String> =  // kotlin.Array<kotlin.String> | 
try {
    apiInstance.testQueryParameterCollectionFormat(pipe, ioutil, http, url, context)
} catch (e: ClientException) {
    println("4xx response calling FakeApi#testQueryParameterCollectionFormat")
    e.printStackTrace()
} catch (e: ServerException) {
    println("5xx response calling FakeApi#testQueryParameterCollectionFormat")
    e.printStackTrace()
}
```

### Parameters

Name | Type | Description  | Notes
------------- | ------------- | ------------- | -------------
 **pipe** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)|  |
 **ioutil** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)|  |
 **http** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)|  |
 **url** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)|  |
 **context** | [**kotlin.Array&lt;kotlin.String&gt;**](kotlin.String.md)|  |

### Return type

null (empty response body)

### Authorization

No authorization required

### HTTP request headers

 - **Content-Type**: Not defined
 - **Accept**: Not defined

