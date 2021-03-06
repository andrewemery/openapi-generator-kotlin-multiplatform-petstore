package org.openapitools.client

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.InputProvider
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.content.TextContent
import io.ktor.http.*
import io.ktor.util.KtorExperimentalAPI
import kotlinx.io.charsets.Charsets
import kotlinx.io.core.buildPacket
import kotlinx.serialization.UnstableDefault
import kotlinx.serialization.json.Json
import org.openapitools.client.apis.*
import org.openapitools.client.infrastructure.Base64ByteArray
import org.openapitools.client.infrastructure.HttpResponse
import org.openapitools.client.infrastructure.OctetByteArray
import org.openapitools.client.models.*
import util.runTest
import kotlin.collections.List
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Created by Andrew Emery on 2019-05-14.
 */
@UnstableDefault
@KtorExperimentalAPI
@UseExperimental(ExperimentalStdlibApi::class)
class OpenApiTest {

    // TODO: bearer test (openapi v3 only)

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * json
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    @Test
    fun `test json request`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val json = "{\"id\":12,\"petId\":3,\"quantity\":1,\"shipDate\":\"2019-06-20T22:38:09.097Z\",\"status\":\"placed\",\"complete\":false}"
        val engine = mockJsonSuccess(json, requests)

        // perform the request
        val response: HttpResponse<Order> = StoreApi("http://example.com", engine).placeOrder(Order(12, 3, 1, "2019-06-20T22:38:09.097Z", Order.Status.placed, false))

        // verify the response
        assertTrue(response.success)
        val order = response.body()
        assertEquals(1, order.quantity)
        assertEquals("2019-06-20T22:38:09.097Z", order.shipDate)
        assertEquals(Order.Status.placed, order.status)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Post, request.method)
        assertEquals(Url("http://example.com/store/order"), request.url)
        assertEquals("12", Json.plain.parseJson((request.body as TextContent).text).jsonObject.getPrimitive("id").content)
    }

    @Test
    fun `test json response`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val json = "{\"id\":1,\"category\":{\"id\":1,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}"
        val engine = mockJsonSuccess(json, requests)

        // perform the request
        val response: HttpResponse<Pet> = PetApi("http://example.com", engine).getPetById(1)

        // verify the response
        assertTrue(response.success)
        val pet = response.body()
        assertEquals(1, pet.id)
        assertEquals("doggie", pet.name)
        assertEquals("string", pet.tags!![0].name)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Get, request.method)
        assertEquals(Url("http://example.com/pet/1"), request.url)
    }

    @Test
    fun `test json generic request`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val engine = mockSuccess(requests)

        // perform the request
        val response: HttpResponse<Unit> = UserApi("http://example.com", engine).createUsersWithArrayInput(arrayOf(User(1), User(2)))

        // verify the response
        assertTrue(response.success)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Post, request.method)
        assertEquals(Url("http://example.com/user/createWithArray"), request.url)
        assertEquals("1", Json.plain.parseJson((request.body as TextContent).text).jsonArray[0].jsonObject.getPrimitive("id").content)
    }

    @Test
    fun `test json generic response`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val json = "[{\"id\":1,\"category\":{\"id\":1,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}]"
        val engine = mockJsonSuccess(json, requests)

        // perform the request
        val response: HttpResponse<Array<Pet>> = PetApi("http://example.com", engine).findPetsByStatus(arrayOf(Pet.Status.available.value, Pet.Status.pending.value))

        // verify the response
        assertTrue(response.success)
        val pets = response.body()
        assertEquals(1, pets.size)
        assertEquals(1, pets[0].id)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Get, request.method)
        assertEquals(Url("http://example.com/pet/findByStatus?status=available%2Cpending"), request.url)
    }

    @Test
    fun `test json primitive response`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val json = "success"
        val engine = mockJsonSuccess(json, requests)

        // perform the request
        val response: HttpResponse<String> = UserApi("http://example.com", engine).loginUser("username", "pass")

        // verify the response
        assertTrue(response.success)
        val string = response.body()
        assertEquals("success", string)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Get, request.method)
        assertEquals(Url("http://example.com/user/login?username=username&password=pass"), request.url)
    }

    @Test
    fun `test json integer enum values response`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val json = "{\"enum_string_required\":\"UPPER\", \"enum_integer\":1, \"enum_number\":1.1}"
        val engine = mockJsonSuccess(json, requests)

        // perform the request
        val enums: HttpResponse<EnumTest> = FakeApi("http://example.com", engine).testEnumData()

        // verify the response
        assertTrue(enums.success)
        val enum = enums.body()
        assertEquals(EnumTest.EnumStringRequired.uPPER, enum.enumStringRequired)
        assertEquals(EnumTest.EnumInteger._1, enum.enumInteger)
        assertEquals(EnumTest.EnumNumber._1period1, enum.enumNumber)
    }

    @Test
    fun `test json byte response`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val json = "{\"number\":1.2, \"byte\":\"IT8=\", \"date\":\"\", \"password\":\"p\", \"binary\":\"213F\"}"
        val engine = mockJsonSuccess(json, requests)

        // perform the request
        val formats: HttpResponse<FormatTest> = FakeApi("http://example.com", engine).testFormats()

        // verify the response
        assertTrue(formats.success)
        val format = formats.body()
        assertEquals(Base64ByteArray(byteArrayOf(0x21.toByte(), 0x3f.toByte())), format.byte)
        assertEquals(OctetByteArray(byteArrayOf(0x21.toByte(), 0x3f.toByte())), format.binary)
    }


    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * url encoded form
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    @Test
    fun `test url encoded form request`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val engine = mockSuccess(requests)

        // perform the request
        val response = PetApi("http://example.com", engine).updatePetWithForm(1, "doggie", "status")

        // verify the response
        assertTrue(response.success)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Post, request.method)
        assertEquals(Url("http://example.com/pet/1"), request.url)
        assertEquals(request.body.contentType, ContentType.Application.FormUrlEncoded.withCharset(Charsets.UTF_8))
        assertTrue((request.body as FormDataContent).formData.contains("name", "doggie"))
        assertTrue((request.body as FormDataContent).formData.contains("status", "status"))
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * multipart request
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    @Test
    fun `test multipart request`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val json = "{\"code\":1,\"type\":\"t\",\"message\":\"m\"}"
        val engine = mockJsonSuccess(json, requests)

        // perform the request
        val response: HttpResponse<ApiResponse> = PetApi("http://example.com", engine).uploadFile(1, "metadata", InputProvider(null) {
            buildPacket { append("cat") }
        })

        // verify the response
        assertTrue(response.success)
        val api: ApiResponse = response.body()
        assertEquals(1, api.code)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Post, request.method)
        assertEquals(Url("http://example.com/pet/1/uploadImage"), request.url)
        assertTrue((request.body is MultiPartFormDataContent))
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * parameters
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    @Test
    fun `test path parameter`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val json = "{\"id\":1,\"category\":{\"id\":1,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}"
        val engine = mockJsonSuccess(json, requests)

        // perform the request
        val response: HttpResponse<Pet> = PetApi("http://example.com", engine).getPetById(1)

        // verify the response
        assertTrue(response.success)
        val pet = response.body()
        assertEquals(1, pet.id)
        assertEquals("doggie", pet.name)
        assertEquals("string", pet.tags!![0].name)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Get, request.method)
        assertEquals(Url("http://example.com/pet/1"), request.url)
    }

    @Test
    fun `test header parameter`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val engine = mockSuccess(requests)

        // perform the request
        val response = PetApi("http://example.com", engine).deletePet(1, "api")

        // verify the response
        assertTrue(response.success)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Delete, request.method)
        assertEquals(Url("http://example.com/pet/1"), request.url)
        assertTrue(request.headers.contains("api_key", "api"))
    }

    @Test
    fun `test query parameter`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val json = "success"
        val engine = mockJsonSuccess(json, requests)

        // perform the request
        val response: HttpResponse<String> = UserApi("http://example.com", engine).loginUser("username", "pass")

        // verify the response
        assertTrue(response.success)
        val string = response.body()
        assertEquals("success", string)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Get, request.method)
        assertEquals(Url("http://example.com/user/login?username=username&password=pass"), request.url)
    }

    @Test
    fun `test multi query parameters`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val engine = mockSuccess(requests)

        // perform the request
        val response: HttpResponse<Unit> = FakeApi("http://example.com", engine).testQueryParameterCollectionFormat(
                arrayOf("pipe1", "pipe2"), arrayOf("tsv1", "tsv2"), arrayOf("ssv1", "ssv2"), arrayOf("csv1", "csv2"), arrayOf("multi1", "multi2"))

        // verify the response
        assertTrue(response.success)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Put, request.method)
        // note: tsv becomes csv when migrated to OpenApi v3:
        // https://github.com/swagger-api/swagger-parser/blob/master/modules/swagger-parser-v2-converter/src/main/java/io/swagger/v3/parser/converter/SwaggerConverter.java
        assertEquals(Url("http://example.com/fake/test-query-paramters?pipe=pipe1|pipe2&ioutil=tsv1,tsv2&http=ssv1%20ssv2&url=csv1,csv2&context=multi1&context=multi2"), request.url)
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * auth
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    @Test
    fun `test basic auth`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val engine = mockSuccess(requests)

        // perform the request
        val response: HttpResponse<Unit> = FakeApi("http://example.com", engine)
                .apply { setUsername("test_username") }.apply { setPassword("test_password") }
                .testEndpointParameters(1.0, 2.0, "pat", Base64ByteArray(byteArrayOf(1.toByte(), 2.toByte())),
                        null, null, null, null, null, null, null, null, null, null)

        // verify the response
        assertTrue(response.success)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Post, request.method)
        assertEquals(Url("http://example.com/fake"), request.url)
        assertTrue(request.headers.contains("Authorization", "Basic dGVzdF91c2VybmFtZTp0ZXN0X3Bhc3N3b3Jk"))
    }

    @Test
    fun `test api token header`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val json = "{\"id\":1,\"category\":{\"id\":1,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}"
        val engine = mockJsonSuccess(json, requests)

        // perform the request
        val response: HttpResponse<Pet> = PetApi("http://example.com", engine)
                .apply { setApiKey("test_key") }
                .getPetById(1)

        // verify the response
        assertTrue(response.success)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Get, request.method)
        assertEquals(Url("http://example.com/pet/1"), request.url)
        assertTrue(request.headers.contains("api_key", "test_key"))
    }

    @Test
    fun `test api token prefix`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val json = "{\"id\":1,\"category\":{\"id\":1,\"name\":\"string\"},\"name\":\"doggie\",\"photoUrls\":[\"string\"],\"tags\":[{\"id\":0,\"name\":\"string\"}],\"status\":\"available\"}"
        val engine = mockJsonSuccess(json, requests)

        // perform the request
        val response: HttpResponse<Pet> = PetApi("http://example.com", engine)
                .apply { setApiKey("test_key") }
                .apply { setApiKeyPrefix("prefix") }
                .getPetById(1)

        // verify the response
        assertTrue(response.success)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Get, request.method)
        assertEquals(Url("http://example.com/pet/1"), request.url)
        assertTrue(request.headers.contains("api_key", "prefix test_key"))
    }


    @Test
    fun `test api token query`(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val json = "{\"client\":\"category\"}"
        val engine = mockJsonSuccess(json, requests)

        // perform the request
        val response: HttpResponse<Client> = FakeClassnameTags123Api("http://example.com", engine)
                .apply { setApiKey("test_key", "api_key_query") }
                .testClassname(Client("client"))

        // verify the response
        assertTrue(response.success)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Patch, request.method)
        assertEquals(Url("http://example.com/fake_classname_test?api_key_query=test_key"), request.url)
    }

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * mock http client
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    private fun mockJsonSuccess(body: String, requests: MutableList<HttpRequestData>? = null): HttpClientEngine {
        return MockEngine.invoke { request: HttpRequestData ->
            requests?.add(request)
            respond(body, headers = headersOf(ContentType.Application.Json.header()))
        }
    }

    private fun mockSuccess(requests: MutableList<HttpRequestData>? = null): HttpClientEngine {
        return MockEngine.invoke { request: HttpRequestData ->
            requests?.add(request)
            respond(byteArrayOf())
        }
    }
}

internal fun ContentType.header(): Pair<String, List<String>> {
    return Pair(HttpHeaders.ContentType, listOf(toString()))
}