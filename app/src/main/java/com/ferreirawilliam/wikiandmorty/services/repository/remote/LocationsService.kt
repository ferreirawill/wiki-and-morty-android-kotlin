package com.ferreirawilliam.wikiandmorty.services.repository.remote

import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import com.ferreirawilliam.wikiandmorty.model.GetLocations
import com.ferreirawilliam.wikiandmorty.model.LocationsModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationsService {
    @GET("location/")
    fun getAllLocations(@Query("page",encoded = true) page: String = ""): Call<GetLocations>
    @GET("location/{characterList}")
    fun getMultipleLocations(@Path("characterList") characterList: List<Int>): Call<List<LocationsModel>>
    @GET("location/{id}")
    fun getSingleLocations(@Path("id") id: Int): Call<LocationsModel>
}