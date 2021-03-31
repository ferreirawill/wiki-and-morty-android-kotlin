package com.ferreirawilliam.wikiandmorty.services.listeners

import com.ferreirawilliam.wikiandmorty.model.GetCharacters

interface CharacterListener {
    fun onSuccess(model: GetCharacters)
    fun onFailure(string: String)

}