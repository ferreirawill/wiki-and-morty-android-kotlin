package com.ferreirawilliam.wikiandmorty.models

import com.ferreirawilliam.wikiandmorty.MockResponseReader

import com.ferreirawilliam.wikiandmorty.model.EpisodesModel
import com.ferreirawilliam.wikiandmorty.model.GetEpisodes
import org.junit.Test

class EpisodeModelTest {
    private lateinit var allEpisodes: GetEpisodes
    private lateinit var multipleEpisodes:List<EpisodesModel>
    private lateinit var singleEpisodes: EpisodesModel


    @Test
    fun allEpisodesResponse(){
        allEpisodes = MockResponseReader.mockGetAllEpisodes(MockResponseReader.Response.EPISODES_PAGE_1)
        assert(allEpisodes.infoModel != null)
        assert(allEpisodes.infoModel?.nextPage == "https://rickandmortyapi.com/api/episode?page=2"
                && allEpisodes.infoModel?.previousPage == null)
        assert(allEpisodes.results.size == 20)
        assert(allEpisodes.infoModel?.pages != 0)
        assert(allEpisodes.infoModel?.count != 0)

        allEpisodes = MockResponseReader.mockGetAllEpisodes(MockResponseReader.Response.EPISODES_PAGE_2)
        assert(allEpisodes.infoModel?.nextPage == "https://rickandmortyapi.com/api/episode/?page=3"
                && allEpisodes.infoModel?.previousPage != null)
        assert(allEpisodes.results.size == 20)
        assert(allEpisodes.infoModel?.pages != 0)
        assert(allEpisodes.infoModel?.count != 0)
    }


    @Test
    fun multipleCharacterResponse(){
        multipleEpisodes = MockResponseReader.mockGetMultipleEpisodes(MockResponseReader.Response.MULTIPLE_EPISODES)
        multipleEpisodes.forEach {
            assert(it.id != 0)
            assert(it.name.isNotEmpty())
            assert(it.airDate.isNotEmpty())
            assert(it.episode.isNotEmpty())
            assert(it.characters.isNotEmpty())
            assert(it.url.isNotEmpty())
        }
    }

    @Test
    fun singleCharacterResponse(){
        singleEpisodes = MockResponseReader.mockGetSingleEpisode(MockResponseReader.Response.SINGLE_EPISODE)

        assert(singleEpisodes.id != 0)
        assert(singleEpisodes.name.isNotEmpty())
        assert(singleEpisodes.airDate.isNotEmpty())
        assert(singleEpisodes.episode.isNotEmpty())
        assert(singleEpisodes.characters.isNotEmpty())
        assert(singleEpisodes.url.isNotEmpty())
    }

}