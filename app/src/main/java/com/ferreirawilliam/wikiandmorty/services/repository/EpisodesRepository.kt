package com.ferreirawilliam.wikiandmorty.services.repository

import com.ferreirawilliam.wikiandmorty.services.repository.remote.CharacterService
import com.ferreirawilliam.wikiandmorty.services.repository.remote.EpisodesService
import com.ferreirawilliam.wikiandmorty.services.repository.remote.RetrofitClient

class EpisodesRepository {
    private val retrofitClient = RetrofitClient.createService(EpisodesService::class.java)

}