package com.io.swagger.client

import util.runTest
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.request.HttpRequestData
import io.ktor.client.request.forms.FormDataContent
import io.ktor.client.request.forms.InputProvider
import io.ktor.client.request.forms.MultiPartFormDataContent
import io.ktor.content.TextContent
import io.ktor.http.*
import io.swagger.client.apis.PetApi
import io.swagger.client.apis.StoreApi
import io.swagger.client.apis.UserApi
import io.swagger.client.infrastructure.HttpResponse
import io.swagger.client.models.ApiResponse
import io.swagger.client.models.Order
import io.swagger.client.models.Pet
import io.swagger.client.models.User
import kotlinx.io.charsets.Charsets
import kotlinx.io.core.buildPacket
import kotlinx.serialization.json.Json
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

/**
 * Created by Andrew Emery on 2019-05-14.
 */
@UseExperimental(ExperimentalStdlibApi::class)
class SwaggerTest {

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * pet api
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    @Test // path parameter, json response
    fun getPet(): Unit = runTest {
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

    @Test // path parameter, url encoded form
    fun updatePetWithForm(): Unit = runTest {
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

    @Test // header parameter
    fun deletePet(): Unit = runTest {
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

    @Test // multipart request
    fun uploadImage(): Unit = runTest {
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

    @Test // generic json response, multi path parameters
    fun findPetsByStatus(): Unit = runTest {
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

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * store api
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    @Test // path parameter, json response
    fun getOrder(): Unit = runTest {
        val requests = mutableListOf<HttpRequestData>()
        val json = "{\"id\":0,\"petId\":0,\"quantity\":0,\"shipDate\":\"2019-06-20T22:38:09.097Z\",\"status\":\"placed\",\"complete\":false}"
        val engine = mockJsonSuccess(json, requests)

        // perform the request
        val response: HttpResponse<Order> = StoreApi("http://example.com", engine).getOrderById(0)

        // verify the response
        assertTrue(response.success)
        val order = response.body()
        assertEquals(0, order.quantity)
        assertEquals("2019-06-20T22:38:09.097Z", order.shipDate)
        assertEquals(Order.Status.placed, order.status)

        // verify the request
        assertEquals(1, requests.size)
        val request = requests[0]
        assertEquals(HttpMethod.Get, request.method)
        assertEquals(Url("http://example.com/store/order/0"), request.url)
    }

    @Test // json request, json response
    fun placeOrder(): Unit = runTest {
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

    /* - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - -
     * user api
     * - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - - */

    @Test // query parameter, json primitive response
    fun loginUser(): Unit = runTest {
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

    @Test // json generic request
    fun createUserWithArray(): Unit = runTest {
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