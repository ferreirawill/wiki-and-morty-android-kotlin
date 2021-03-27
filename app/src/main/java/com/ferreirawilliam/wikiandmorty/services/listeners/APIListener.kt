package com.ferreirawilliam.wikiandmorty.services.listeners

import com.ferreirawilliam.wikiandmorty.model.CharacterResult
import com.ferreirawilliam.wikiandmorty.model.GetCharacters

interface APIListener {
    fun onSuccess(model: GetCharacters)
    fun onFailure(string: String)

}