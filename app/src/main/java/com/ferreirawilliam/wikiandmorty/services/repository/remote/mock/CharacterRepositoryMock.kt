package com.ferreirawilliam.wikiandmorty.services.repository.remote.mock

import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import com.ferreirawilliam.wikiandmorty.services.listeners.CharacterListener
import com.ferreirawilliam.wikiandmorty.services.repository.remote.CharacterService
import okhttp3.HttpUrl
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepositoryMock(serverUrl: HttpUrl) {
    private var retrofitClient:CharacterService

    init {
        RetrofitClientMock.SERVER_DATA.URL = serverUrl
        retrofitClient = RetrofitClientMock.createService(CharacterService::class.java)
    }

    fun getAllCharacters(listener:CharacterListener){
        val call:Call<GetCharacters> =  retrofitClient.getAllCharacters()



        call.enqueue(object :Callback<GetCharacters>{
            override fun onFailure(call: Call<GetCharacters>, t: Throwable) {

                listener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<GetCharacters>, response: Response<GetCharacters>) {

                response.body()?.let { listener.onSuccess(it) }

            }

        })
    }

    fun getMultipleCharacters(characterList: List<Int>,listener: CharacterListener) {

        val call:Call<List<CharacterModel>> =  retrofitClient.getMultipleCharacters(characterList)


        call.enqueue(object :Callback<List<CharacterModel>>{
            override fun onFailure(call: Call<List<CharacterModel>>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<List<CharacterModel>>, response: Response<List<CharacterModel>>) {
                response.body()?.let { listener.onSuccess(GetCharacters(infoModel = null, results = it)) }
            }

        })

    }

    fun getSingleCharacters(characterId:Int,listener: CharacterListener) {
        val call:Call<CharacterModel> =  retrofitClient.getSingleCharacters(characterId)


        call.enqueue(object :Callback<CharacterModel>{
            override fun onFailure(call: Call<CharacterModel>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<CharacterModel>, response: Response<CharacterModel>) {
                response.body()?.let { listener.onSuccess(GetCharacters(infoModel = null, results = arrayListOf(it))) }
            }

        })

    }

}