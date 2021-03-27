package com.ferreirawilliam.wikiandmorty.services.repository.remote

import android.util.Log
import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import com.ferreirawilliam.wikiandmorty.services.listeners.APIListener
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository {
    private val retrofitClient = RetrofitClient.createService(CharacterService::class.java)

    fun getAllCharacters(listener:APIListener){
        val call:Call<GetCharacters> =  retrofitClient.getAllCharacters()

        Log.d("API REQUEST", "CHAMANDO API")

        call.enqueue(object :Callback<GetCharacters>{
            override fun onFailure(call: Call<GetCharacters>, t: Throwable) {
                Log.d("API REQUEST", "onFailure: ${t.message.toString()}")
                listener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<GetCharacters>, response: Response<GetCharacters>) {
                Log.d("API REQUEST", "SUCESSO NA API")
                response.body()?.let { listener.onSuccess(it) }

            }

        })
    }

}