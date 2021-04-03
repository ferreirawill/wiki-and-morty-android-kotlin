package com.ferreirawilliam.wikiandmorty.models

import com.ferreirawilliam.wikiandmorty.MockResponseReader
import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import org.junit.Before
import org.junit.Test

class CharacterModelsTest {
    private lateinit var allCharacter:GetCharacters
    private lateinit var multipleCharacter:List<CharacterModel>
    private lateinit var singleCharacter:CharacterModel

    @Test
    fun allCharacterResponse(){
        allCharacter = MockResponseReader.mockGetAllCharacters(MockResponseReader.Response.CHARACTERS_PAGE_1)
        assert(allCharacter.infoModel != null)
        assert(allCharacter.infoModel?.nextPage =="https://rickandmortyapi.com/api/character/?page=2"
                && allCharacter.infoModel?.previousPage == null)
        assert(allCharacter.infoModel?.pages != 0)
        assert(allCharacter.infoModel?.count != 0)

        allCharacter = MockResponseReader.mockGetAllCharacters(MockResponseReader.Response.CHARACTERS_PAGE_2)

        assert(allCharacter.infoModel != null)
        assert(allCharacter.infoModel?.nextPage =="https://rickandmortyapi.com/api/character/?page=3"
                && allCharacter.infoModel?.previousPage == "https://rickandmortyapi.com/api/character/?page=1")
        assert(allCharacter.results.size == 20)
        assert(allCharacter.infoModel?.pages != 0)
        assert(allCharacter.infoModel?.count != 0)
    }
    @Test
    fun multipleCharacterResponse(){
        multipleCharacter = MockResponseReader.mockGetMultipleCharacter(MockResponseReader.Response.MULTIPLE_CHARACTERS)

        multipleCharacter.forEach {
            assert(it.id != 0)
            assert(it.name.isNotEmpty())
            assert(it.status.isNotEmpty())
            assert(it.species.isNotEmpty())
            assert(it.gender.isNotEmpty())
            assert(it.origin.containsKey("name"))
            assert(it.origin.containsKey("url"))
            assert(it.location.containsKey("name"))
            assert(it.location.containsKey("url"))
            assert(it.episode.isNotEmpty())
            assert(it.image.isNotEmpty())
            assert(it.url.isNotEmpty())
        }


    }
    @Test
    fun singleCharacterResponse(){
        singleCharacter = MockResponseReader.mockGetSingleCharacter(MockResponseReader.Response.SINGLE_CHARACTERS)


        assert(singleCharacter.id == 1)
        assert(singleCharacter.name.isNotEmpty())
        assert(singleCharacter.status.isNotEmpty())
        assert(singleCharacter.species.isNotEmpty())
        assert(singleCharacter.gender.isNotEmpty())
        assert(singleCharacter.origin.containsKey("name"))
        assert(singleCharacter.origin.containsKey("url"))
        assert(singleCharacter.location.containsKey("name"))
        assert(singleCharacter.location.containsKey("url"))
        assert(singleCharacter.episode.isNotEmpty())
        assert(singleCharacter.image.isNotEmpty())
        assert(singleCharacter.url.isNotEmpty())
    }
}