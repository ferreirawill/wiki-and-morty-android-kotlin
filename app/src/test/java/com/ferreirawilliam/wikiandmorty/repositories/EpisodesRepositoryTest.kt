package com.ferreirawilliam.wikiandmorty.repositories


import com.ferreirawilliam.wikiandmorty.MockResponseReader
import com.ferreirawilliam.wikiandmorty.services.repository.remote.mock.CharacterRepositoryMock
import okhttp3.mockwebserver.Dispatcher
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import okhttp3.mockwebserver.RecordedRequest
import org.junit.After
import org.junit.Before
import org.junit.Test
import java.util.concurrent.TimeUnit


class EpisodesRepositoryTest {
    private lateinit var mEpisodeRepository: CharacterRepositoryMock
    private val server = MockWebServer()
/*
    @Before
    fun setUpTests(){
        val allEpisodesResponse = MockResponseReader.readFile(MockResponseReader.Response.ALL_EPISODES)
        val multipleEpisodesResponse = MockResponseReader.readFile(MockResponseReader.Response.MULTIPLE_EPISODES)
        val singleEpisodesResponse = MockResponseReader.readFile(MockResponseReader.Response.SINGLE_EPISODE)
        server.start()
        mEpisodeRepository = CharacterRepositoryMock(serverUrl = server.url(""))

        val dispatcher: Dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                when (request.path) {
                    "/episode/" -> return MockResponse().setBody(allEpisodesResponse)
                            .setResponseCode(200)
                            .addHeader("Content-Type", "application/json; charset=utf-8")
                            .addHeader("Cache-Control", "no-cache").throttleBody(1,period = 2,unit = TimeUnit.SECONDS)
                    "/episode/[1,2,3]" -> return MockResponse().setBody(multipleEpisodesResponse)
                            .setResponseCode(200)
                            .addHeader("Content-Type", "application/json; charset=utf-8")
                            .addHeader("Cache-Control", "no-cache").throttleBody(1,period = 2,unit = TimeUnit.SECONDS)
                    "/episode/1" -> return MockResponse().setBody(singleEpisodesResponse)
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


        assert(request?.path.equals("/episode/"))
        assert(request?.method == "GET")
    }

    @Test
    fun testGetSingleCharacters(){


        val episodeId = 1


        var request = server.takeRequest(5, TimeUnit.SECONDS)


        assert(request?.path.equals("/episode/$episodeId"))
        assert(request?.method == "GET")

    }

    @After
    fun closeAll(){
        server.shutdown();
    }
*/

}