package com.ferreirawilliam.wikiandmorty.services.repository


import com.ferreirawilliam.wikiandmorty.model.GetLocations
import com.ferreirawilliam.wikiandmorty.services.listeners.LocationsListener
import com.ferreirawilliam.wikiandmorty.services.repository.remote.LocationsService


import com.ferreirawilliam.wikiandmorty.services.repository.remote.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LocationsRepository {
    private val retrofitClient = RetrofitClient.createService(LocationsService::class.java)

    fun getAll(listener:LocationsListener){
        val call = retrofitClient.getAllLocations()

        call.enqueue(object : Callback<GetLocations> {
            override fun onFailure(call: Call<GetLocations>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<GetLocations>, response: Response<GetLocations>) {
                response.body()?.let { listener.onSuccess(it) }
            }


        })

    }
    fun getMultiple(list:List<String>,listener:LocationsListener){
        TODO("NEED TO CONVERT URL LIST TO ID LIST ")
    }
    fun getSingle(url:String,listener:LocationsListener){
        TODO("NEED TO CONVERT URL TO ID")
    }
    fun getNewPage(urlPage:String,listener:LocationsListener){
        if(urlPage.isEmpty()) return

        var nextPage = urlPage.split("/?page=").last()

        val call = retrofitClient.getAllLocations(nextPage)

        call.enqueue(object : Callback<GetLocations>{
            override fun onFailure(call: Call<GetLocations>, t: Throwable) {
                listener.onFailure(t.message.toString())
            }

            override fun onResponse(call: Call<GetLocations>, response: Response<GetLocations>) {
                response.body()?.let { listener.onSuccess(it) }
            }

        })

    }
}