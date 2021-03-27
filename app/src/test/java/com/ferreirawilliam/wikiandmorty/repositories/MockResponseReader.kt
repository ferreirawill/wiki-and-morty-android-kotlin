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
        const val CHARACTERS= "characters.json"
    }
}