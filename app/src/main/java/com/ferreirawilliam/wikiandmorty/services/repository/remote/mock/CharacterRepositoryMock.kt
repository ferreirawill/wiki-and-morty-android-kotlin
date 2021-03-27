package com.ferreirawilliam.wikiandmorty.services.repository.remote.mock

import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import com.ferreirawilliam.wikiandmorty.services.listeners.APIListener
import com.ferreirawilliam.wikiandmorty.services.repository.remote.CharacterService
import com.ferreirawilliam.wikiandmorty.services.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepositoryMock {
    private val retrofitClient = RetrofitClientMock.createService(CharacterService::class.java)

    fun getAllCharacters(listener:APIListener){
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
}