package com.ferreirawilliam.wikiandmorty

import com.ferreirawilliam.wikiandmorty.model.*
import com.google.gson.Gson
import java.io.FileNotFoundException
import java.net.URL

class MockResponseReader private constructor(){

    companion object{
        fun readFile(path: String): String {
            val content: URL? = ClassLoader.getSystemResource(path)

            return content?.readText() ?: throw FileNotFoundException("File path was not found")
        }

        fun mockGetAllCharacters(path: String):GetCharacters{
            val response = readFile(path)
            val gson = Gson()
            return gson.fromJson(response,GetCharacters::class.java)
        }
        fun mockGetMultipleCharacter(path: String):List<CharacterModel>{
            val response = readFile(path)
            val gson = Gson()
            return  gson.fromJson(response,Array<CharacterModel>::class.java).asList()
        }
        fun mockGetSingleCharacter(path: String):CharacterModel{
            val response = readFile(path)
            val gson = Gson()
            return gson.fromJson(response,CharacterModel::class.java)
        }


        fun mockGetAllEpisodes(path: String):GetEpisodes{
            val response = readFile(path)
            val gson = Gson()
            return gson.fromJson(response,GetEpisodes::class.java)
        }
        fun mockGetMultipleEpisodes(path: String):List<EpisodesModel>{
            val response = readFile(path)
            val gson = Gson()
            return  gson.fromJson(response,Array<EpisodesModel>::class.java).asList()
        }
        fun mockGetSingleEpisode(path: String):EpisodesModel{
            val response = readFile(path)
            val gson = Gson()
            return gson.fromJson(response,EpisodesModel::class.java)
        }

        fun mockGetAllLocations(path: String):GetLocations{
            val response = readFile(path)
            val gson = Gson()
            return gson.fromJson(response,GetLocations::class.java)
        }
        fun mockGetMultipleLocations(path: String):List<LocationsModel>{
            val response = readFile(path)
            val gson = Gson()
            return  gson.fromJson(response,Array<LocationsModel>::class.java).asList()
        }
        fun mockGetSingleLocation(path: String):LocationsModel{
            val response = readFile(path)
            val gson = Gson()
            return gson.fromJson(response,LocationsModel::class.java)
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

        const val EPISODES_PAGE_1= "episodes_page_1.json"
        const val EPISODES_PAGE_2= "episodes_page_2.json"


        const val LOCATIONS_PAGE_1= "locations_page_1.json"
        const val LOCATIONS_PAGE_2= "locations_page_2.json"

        const val MULTIPLE_LOCATIONS= "multipleLocations.json"
        const val MULTIPLE_EPISODES= "multipleEpisodes.json"
        const val MULTIPLE_CHARACTERS= "multipleCharacters.json"

        const val SINGLE_CHARACTERS= "singleCharacter.json"
        const val SINGLE_EPISODE= "singleEpisode.json"
        const val SINGLE_LOCATION= "singleLocation.json"
    }



}