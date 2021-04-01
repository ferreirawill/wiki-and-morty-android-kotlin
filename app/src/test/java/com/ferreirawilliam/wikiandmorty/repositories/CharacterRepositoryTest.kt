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


class CharacterRepositoryTest{

    private lateinit var mCharacterRepository:CharacterRepositoryMock
    private val server = MockWebServer()



    @Before
    fun setUpTests(){
        val allCharactersResponse = MockResponseReader.readFile(MockResponseReader.Response.CHARACTERS_PAGE_1)
        val multipleCharactersResponse = MockResponseReader.readFile(MockResponseReader.Response.MULTIPLE_CHARACTERS)
        val singleCharactersResponse = MockResponseReader.readFile(MockResponseReader.Response.SINGLE_CHARACTERS)
        server.start()
        mCharacterRepository = CharacterRepositoryMock(serverUrl = server.url(""))

        val dispatcher: Dispatcher = object : Dispatcher() {
            override fun dispatch(request: RecordedRequest): MockResponse {
                when (request.path) {
                    "/character/" -> return MockResponse().setBody(allCharactersResponse)
                            .setResponseCode(200)
                            .addHeader("Content-Type", "application/json; charset=utf-8")
                            .addHeader("Cache-Control", "no-cache").throttleBody(1,period = 2,unit = TimeUnit.SECONDS)
                    "/character/[1,2,3]" -> return MockResponse().setBody(multipleCharactersResponse)
                            .setResponseCode(200)
                            .addHeader("Content-Type", "application/json; charset=utf-8")
                            .addHeader("Cache-Control", "no-cache").throttleBody(1,period = 2,unit = TimeUnit.SECONDS)
                    "/character/1" -> return MockResponse().setBody(singleCharactersResponse)
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


        mCharacterRepository.getAllCharacters(object :CharacterListener{
            override fun onSuccess(model: GetCharacters) {
                assert(model.infoModel != null)
            }

            override fun onFailure(string: String) {
                assert(string.isNotEmpty())
            }

        })

        var request = server.takeRequest(5,TimeUnit.SECONDS)


        assert(request?.path.equals("/character/"))
        assert(request?.method == "GET")
    }


    @Test
    fun testParser(){
        var getCharactersModel = MockResponseReader.parseToCharacters(MockResponseReader.Response.CHARACTERS_PAGE_1)

        assert(getCharactersModel.infoModel != null)
    }

    @Test
    fun testGetMultipleCharacters(){

        val characterList = arrayListOf(1,2,3)
        mCharacterRepository.getMultipleCharacters(characterList,object :CharacterListener{
            override fun onSuccess(model: GetCharacters) {
                assert(model.results.isNotEmpty())
            }

            override fun onFailure(string: String) {
                assert(string.isNotEmpty())
            }

        })

        var request = server.takeRequest(5,TimeUnit.SECONDS)

        assert(request?.path != null)
        assert(request?.method == "GET")
    }

    @Test
    fun testGetSingleCharacters(){


        val characterId = 1
        mCharacterRepository.getSingleCharacters(characterId,object :CharacterListener{
            override fun onSuccess(model: GetCharacters) {
                assert(model.results.isNotEmpty())
            }

            override fun onFailure(string: String) {
                assert(string.isNotEmpty())
            }

        })

        var request = server.takeRequest(5,TimeUnit.SECONDS)


        assert(request?.path.equals("/character/$characterId"))
        assert(request?.method == "GET")

    }

    @After
    fun closeAll(){
        server.shutdown();
    }


}