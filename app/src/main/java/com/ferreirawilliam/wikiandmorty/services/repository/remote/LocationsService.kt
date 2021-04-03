package com.ferreirawilliam.wikiandmorty.services.repository.remote

import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface LocationsService {
    @GET("location/")
    fun getAllCharacters(): Call<GetCharacters>
    @GET("location/{characterList}")
    fun getMultipleCharacters(@Path("characterList") characterList: List<Int>): Call<List<CharacterModel>>
    @GET("location/{id}")
    fun getSingleCharacters(@Path("id") id: Int): Call<CharacterModel>
}