package com.ferreirawilliam.wikiandmorty.services.repository.remote

import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CharacterService {
    @GET("character/")
    fun getAllCharacters(@Query("page",encoded = true) page: String = ""):Call<GetCharacters>
    @GET("character/{characterList}")
    fun getMultipleCharacters(@Path("characterList") characterList: List<Int>):Call<List<CharacterModel>>
    @GET("character/{id}")
    fun getSingleCharacters(@Path("id") id: Int):Call<CharacterModel>

}