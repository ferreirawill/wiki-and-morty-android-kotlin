package com.ferreirawilliam.wikiandmorty.services.repository

import com.ferreirawilliam.wikiandmorty.services.repository.remote.CharacterService
import com.ferreirawilliam.wikiandmorty.services.repository.remote.LocationsService
import com.ferreirawilliam.wikiandmorty.services.repository.remote.RetrofitClient

class LocationsRepository {
    private val retrofitClient = RetrofitClient.createService(LocationsService::class.java)

}