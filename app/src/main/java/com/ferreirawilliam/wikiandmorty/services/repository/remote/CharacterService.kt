package com.ferreirawilliam.wikiandmorty.services.repository.remote

import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import retrofit2.Call
import retrofit2.http.GET

interface CharacterService {
    @GET("character/")
    fun getAllCharacters():Call<GetCharacters>
}