package com.ferreirawilliam.wikiandmorty.services.repository.remote

import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.model.EpisodesModel
import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import com.ferreirawilliam.wikiandmorty.model.GetEpisodes
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface EpisodesService {
    @GET("episode/")
    fun getAllEpisodes(@Query("page",encoded = true) page: String = ""): Call<GetEpisodes>
    @GET("episode/{episodesList}")
    fun getMultipleEpisodes(@Path("episodesList") episodesList: List<Int>): Call<List<EpisodesModel>>
    @GET("episode/{id}")
    fun getSingleEpisodes(@Path("id") id: Int): Call<EpisodesModel>
}