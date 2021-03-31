package com.ferreirawilliam.wikiandmorty.repositories

import java.io.FileNotFoundException
import java.net.URL

class MockResponseReader private constructor(){

    companion object{
        fun readFile(path: String): String {
            val content: URL? = ClassLoader.getSystemResource(path)

            return content?.readText() ?: throw FileNotFoundException("File path was not found")
        }
    }

    object Response{
        const val ALL_CHARACTERS= "characters.json"
        const val MULTIPLE_CHARACTERS= "multipleCharacters.json"
        const val SINGLE_CHARACTERS= "singleCharacters.json"
        const val ALL_EPISODES= "episodes.json"
        const val MULTIPLE_EPISODES= "multipleEpisodes.json"
        const val SINGLE_EPISODE= "singleEpisode.json"
        const val ALL_LOCATIONS= "locations.json"
        const val MULTIPLE_LOCATIONS= "multipleLocations.json"
        const val SINGLE_LOCATION= "singleLocation.json"
    }
}