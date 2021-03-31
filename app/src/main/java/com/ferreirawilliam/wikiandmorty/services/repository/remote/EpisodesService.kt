package com.ferreirawilliam.wikiandmorty.services.repository.remote

import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface EpisodesService {
    @GET("episode/")
    fun getAllEpisodes(): Call<GetCharacters>
    @GET("episode/{episodesList}")
    fun getMultipleEpisodes(@Path("episodesList") episodesList: List<Int>): Call<List<CharacterModel>>
    @GET("episode/{id}")
    fun getSingleEpisodes(@Path("id") id: Int): Call<CharacterModel>
}