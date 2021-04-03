package com.ferreirawilliam.wikiandmorty.repositories



import com.ferreirawilliam.wikiandmorty.MockResponseReader
import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import com.ferreirawilliam.wikiandmorty.services.listeners.CharacterListener
import com.ferreirawilliam.wikiandmorty.services.repository.remote.mock.CharacterRepositoryMock
import kotlinx.coroutines.runBlocking
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
    fun testCharacterService() {

        //Declaring
        var requestAll: RecordedRequest? = null
        var requestMultiple: RecordedRequest? = null
        var requestSingle: RecordedRequest? = null

        val characterList = arrayListOf(1,2,3)


        // Requests
        mCharacterRepository.getAllCharacters(object :CharacterListener{
            override fun onSuccess(model: GetCharacters) {
                assert(model.infoModel != null)
            }

            override fun onFailure(string: String) {
                assert(string.isNotEmpty())
            }

        })

        requestAll = server.takeRequest(5,TimeUnit.SECONDS)

        mCharacterRepository.getMultipleCharacters(characterList,object :CharacterListener{
            override fun onSuccess(model: GetCharacters) {
                assert(model.results.isNotEmpty())
            }

            override fun onFailure(string: String) {
                assert(string.isNotEmpty())
            }

        })

        requestMultiple = server.takeRequest(5,TimeUnit.SECONDS)

        mCharacterRepository.getSingleCharacters(characterList[0],object :CharacterListener{
            override fun onSuccess(model: GetCharacters) {
                assert(model.results.isNotEmpty())
            }

            override fun onFailure(string: String) {
                assert(string.isNotEmpty())
            }

        })

        requestSingle = server.takeRequest(5,TimeUnit.SECONDS)


        //Assert
        assert(requestAll?.path.equals("/character/?page="))
        assert(requestAll?.method == "GET")

        assert(requestMultiple?.path != null)
        assert(requestMultiple?.method == "GET")

        assert(requestSingle?.path.equals("/character/${characterList[0]}"))
        assert(requestSingle?.method == "GET")
    }




    @After
    fun closeAll(){
        server.shutdown();
    }


}