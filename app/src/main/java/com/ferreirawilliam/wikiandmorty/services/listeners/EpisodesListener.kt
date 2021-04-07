package com.ferreirawilliam.wikiandmorty.services.listeners

import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import com.ferreirawilliam.wikiandmorty.model.GetEpisodes

interface EpisodesListener {
    fun onSuccess(model: GetEpisodes)
    fun onFailure(string: String)

}