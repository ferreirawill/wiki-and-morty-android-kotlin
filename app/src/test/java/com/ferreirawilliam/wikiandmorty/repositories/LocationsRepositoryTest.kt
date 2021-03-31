package com.ferreirawilliam.wikiandmorty.repositories

import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import com.ferreirawilliam.wikiandmorty.services.listeners.CharacterListener
import com.ferreirawilliam.wikiandmorty.services.repository.remote.mock.CharacterRepositoryMock
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit

class LocationsRepositoryTest {
    private lateinit var mLocationRepository: CharacterRepositoryMock
    private val server = MockWebServer()

    @Before
    fun setUpTests(){
        val allLocationsResponse = MockResponseReader.readFile(MockResponseReader.Response.ALL_LOCATIONS)
        val multipleLocationsResponse = MockResponseReader.readFile(MockResponseReader.Response.MULTIPLE_LOCATIONS)
        val singleLocationsResponse = MockResponseReader.readFile(MockResponseReader.Response.SINGLE_LOCATION)
        server.start()
        mLocationRepository = CharacterRepositoryMock(serverUrl = server.url(""))

        val dispatcher: Dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                when (request.path) {
                    "/location/" -> return MockResponse().setBody(allLocationsResponse)
                            .setResponseCode(200)
                            .addHeader("Content-Type", "application/json; charset=utf-8")
                            .addHeader("Cache-Control", "no-cache").throttleBody(1,period = 2,unit = TimeUnit.SECONDS)
                    "/location/[1,2,3]" -> return MockResponse().setBody(multipleLocationsResponse)
                            .setResponseCode(200)
                            .addHeader("Content-Type", "application/json; charset=utf-8")
                            .addHeader("Cache-Control", "no-cache").throttleBody(1,period = 2,unit = TimeUnit.SECONDS)
                    "/location/1" -> return MockResponse().setBody(singleLocationsResponse)
                            .setResponseCode(200)
                            .addHeader("Content-Type", "application/json; charset=utf-8")
                            .addHeader("Cache-Control", "no-cache").throttleBody(1,period = 2,unit = TimeUnit.SECONDS)
                }
                return MockResponse().setResponseCode(404)
            }
        }
        server.enqueue(MockResponse().setResponseCode(400).setBody("{}"))
        server.dispatcher = dispatcher
    }

    @Test
    fun testGetAllCharacters(){



        var request = server.takeRequest(5, TimeUnit.SECONDS)


        assert(request?.path.equals("/character/"))
        assert(request?.method == "GET")
    }

    @Test
    fun testGetMultipleCharacters(){


        var request = server.takeRequest(5, TimeUnit.SECONDS)

        assert(request?.path != null)
        assert(request?.method == "GET")
    }

    @Test
    fun testGetSingleCharacters(){


        val characterId = 1


        var request = server.takeRequest(5, TimeUnit.SECONDS)


        assert(request?.path.equals("/character/$characterId"))
        assert(request?.method == "GET")

    }

    @After
    fun closeAll(){
        server.shutdown();
    }



}