package com.ferreirawilliam.wikiandmorty.services.listeners

import com.ferreirawilliam.wikiandmorty.model.GetLocations

interface LocationsListener {
    fun onSuccess(model: GetLocations)
    fun onFailure(string: String)

}