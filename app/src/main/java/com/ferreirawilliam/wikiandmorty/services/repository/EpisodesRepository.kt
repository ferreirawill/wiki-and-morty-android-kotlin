package com.ferreirawilliam.wikiandmorty.services.repository

import android.util.Log
import com.ferreirawilliam.wikiandmorty.model.EpisodesModel
import com.ferreirawilliam.wikiandmorty.model.GetEpisodes
import com.ferreirawilliam.wikiandmorty.model.GetLocations
import com.ferreirawilliam.wikiandmorty.services.listeners.CharacterListener
import com.ferreirawilliam.wikiandmorty.services.listeners.EpisodesListener
import com.ferreirawilliam.wikiandmorty.services.repository.remote.CharacterService
import com.ferreirawilliam.wikiandmorty.services.repository.remote.EpisodesService
import com.ferreirawilliam.wikiandmorty.services.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class EpisodesRepository {
    private val retrofitClient = RetrofitClient.createService(EpisodesService::class.java)

    fun getAll(listener: EpisodesListener){
        val call = retrofitClient.getAllEpisodes()

        call.enqueue(object : Callback<GetEpisodes>{
            override fun onFailure(call: Call<GetEpisodes>, t: Throwable) {
                Log.d("API REQUEST", "onFailure: ${t.message.toString()}")
                listener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<GetEpisodes>, response: Response<GetEpisodes>) {
                Log.d("API REQUEST", "SUCESSO NA API")
                response.body()?.let { listener.onSuccess(it) }
            }

        })
    }

    fun getMultiple(list: List<String>,listener: EpisodesListener){
        TODO("NEED TO CONVERT THE PAGES LIST TO ID LIST")

        val call = retrofitClient.getMultipleEpisodes(arrayListOf(1,2,3))

        call.enqueue(object :Callback<List<EpisodesModel>>{
            override fun onFailure(call: Call<List<EpisodesModel>>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<List<EpisodesModel>>, response: Response<List<EpisodesModel>>) {
                response.body()?.let {
                    listener.onSuccess(GetEpisodes(null,results = it))
                }
            }

        })
    }

    fun getSingle(id: Int,listener: EpisodesListener){
        val call = retrofitClient.getSingleEpisodes(id)

        call.enqueue(object :Callback<EpisodesModel>{
            override fun onFailure(call: Call<EpisodesModel>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<EpisodesModel>, response: Response<EpisodesModel>) {
               response.body()?.let {
                   listener.onSuccess(GetEpisodes(infoModel = null,results = arrayListOf(it)))
               }
            }


        })
    }

    fun getNewPage(page:String,listener: EpisodesListener){
        if (page.isEmpty()) return
        var nextPage = page.split("/?page=").last()

        val call = retrofitClient.getAllEpisodes(nextPage)

        call.enqueue(object :Callback<GetEpisodes>{
            override fun onFailure(call: Call<GetEpisodes>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<GetEpisodes>, response: Response<GetEpisodes>) {
                response.body()?.let {
                    listener.onSuccess(it)
                }
            }

        })

    }
}