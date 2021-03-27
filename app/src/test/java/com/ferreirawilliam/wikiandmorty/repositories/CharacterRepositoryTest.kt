package com.ferreirawilliam.wikiandmorty.repositories


import android.util.Log
import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import com.ferreirawilliam.wikiandmorty.services.listeners.APIListener
import com.ferreirawilliam.wikiandmorty.services.repository.remote.CharacterRepository
import com.ferreirawilliam.wikiandmorty.services.repository.remote.mock.CharacterRepositoryMock
import com.ferreirawilliam.wikiandmorty.services.repository.remote.mock.RetrofitClientMock
import kotlinx.coroutines.runBlocking
import okhttp3.mockwebserver.MockResponse
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.MockedConstruction
import org.mockito.MockitoAnnotations
import org.robolectric.annotation.Config
import retrofit2.Call
import java.io.FileNotFoundException
import java.net.URL


class CharacterRepositoryTest{

    private val mCharacterRepository = CharacterRepositoryMock()
    private val server = MockWebServer()



    @Before
    fun setUpTests(){
        val response = MockResponseReader.readFile(MockResponseReader.Response.CHARACTERS)
        server.start()
        server.url(RetrofitClientMock.SERVER_DATA.URL+"character/")
        server.enqueue(MockResponse().setBody(response).setResponseCode(200))
    }

    @Test
    fun testGetAllCharacters() = runBlocking {

            mCharacterRepository.getAllCharacters(object :APIListener{
                override fun onSuccess(model: GetCharacters) {
                    print(model)
                }

                override fun onFailure(string: String) {
                    print(string)
                }

            })

            val request = server.takeRequest()

            server.shutdown()
            print(request.path)

            assert(request.path.equals("character/"))


        }

    @After
    fun closeAll(){
        server.shutdown();
    }


}