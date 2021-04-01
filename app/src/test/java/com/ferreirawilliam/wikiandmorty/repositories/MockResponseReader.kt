package com.ferreirawilliam.wikiandmorty.repositories

import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import com.ferreirawilliam.wikiandmorty.model.GetEpisodes
import com.ferreirawilliam.wikiandmorty.model.GetLocations
import com.google.gson.Gson
import java.io.FileNotFoundException
import java.net.URL

class MockResponseReader private constructor(){

    companion object{
        fun readFile(path: String): String {
            val content: URL? = ClassLoader.getSystemResource(path)

            return content?.readText() ?: throw FileNotFoundException("File path was not found")
        }

        fun parseToCharacters(path: String):GetCharacters{
            val response = readFile(path)
            val gson = Gson()
            return gson.fromJson(response,GetCharacters::class.java)
        }

        fun parseToEpisodes(path: String):GetEpisodes{
            val response = readFile(path)
            val gson = Gson()
            return gson.fromJson(response,GetEpisodes::class.java)
        }

        fun parseToLocations(path: String):GetLocations{
            val response = readFile(path)
            val gson = Gson()
            return gson.fromJson(response,GetLocations::class.java)
        }
    }

    object Response{
        const val CHARACTERS_PAGE_1= "characters_page_1.json"
        const val CHARACTERS_PAGE_2= "characters_page_2.json"

        const val ALL_EPISODES= "episodes_page_1.json"
        const val ALL_LOCATIONS= "locations_page_1.json"

        const val MULTIPLE_LOCATIONS= "multipleLocations.json"
        const val MULTIPLE_EPISODES= "multipleEpisodes.json"
        const val MULTIPLE_CHARACTERS= "multipleCharacters.json"

        const val SINGLE_CHARACTERS= "singleCharacters.json"
        const val SINGLE_EPISODE= "singleEpisode.json"
        const val SINGLE_LOCATION= "singleLocation.json"
    }



}