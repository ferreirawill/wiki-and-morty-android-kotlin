package com.ferreirawilliam.wikiandmorty.services.repository

import android.util.Log
import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import com.ferreirawilliam.wikiandmorty.services.listeners.CharacterListener
import com.ferreirawilliam.wikiandmorty.services.repository.remote.CharacterService
import com.ferreirawilliam.wikiandmorty.services.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CharacterRepository {
    private val retrofitClient = RetrofitClient.createService(CharacterService::class.java)

    fun getAllCharacters(listener:CharacterListener){
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

    fun getMultipleCharacters(characterList: List<Int>,listener: CharacterListener) {

        val call:Call<List<CharacterModel>> =  retrofitClient.getMultipleCharacters(characterList)

        Log.d("API REQUEST", "CHAMANDO API")
        call.enqueue(object :Callback<List<CharacterModel>>{
            override fun onFailure(call: Call<List<CharacterModel>>, t: Throwable) {
                Log.d("API REQUEST", "onFailure: ${t.message.toString()}")
                listener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<List<CharacterModel>>, response: Response<List<CharacterModel>>) {
                Log.d("API REQUEST", "SUCESSO NA API")
                response.body()?.let { listener.onSuccess(GetCharacters(infoModel = null,results = it)) }
            }

        })

    }

    fun getSingleCharacters(characterId:Int,listener: CharacterListener) {
        val call:Call<CharacterModel> =  retrofitClient.getSingleCharacters(characterId)

        Log.d("API REQUEST", "CHAMANDO API")
        call.enqueue(object :Callback<CharacterModel>{
            override fun onFailure(call: Call<CharacterModel>, t: Throwable) {
                Log.d("API REQUEST", "onFailure: ${t.message.toString()}")
                listener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<CharacterModel>, response: Response<CharacterModel>) {
                Log.d("API REQUEST", "SUCESSO NA API")
                response.body()?.let { listener.onSuccess(GetCharacters(infoModel = null,results = arrayListOf(it))) }
            }

        })

    }

}