package com.ferreirawilliam.wikiandmorty.models

import com.ferreirawilliam.wikiandmorty.MockResponseReader
import com.ferreirawilliam.wikiandmorty.model.EpisodesModel
import com.ferreirawilliam.wikiandmorty.model.GetEpisodes
import com.ferreirawilliam.wikiandmorty.model.GetLocations
import com.ferreirawilliam.wikiandmorty.model.LocationsModel
import org.junit.Test

class LocationModelsTest {

    private lateinit var allLocations: GetLocations
    private lateinit var multipleLocation:List<LocationsModel>
    private lateinit var singleLocation: LocationsModel


    @Test
    fun allEpisodesResponse(){
        allLocations = MockResponseReader.mockGetAllLocations(MockResponseReader.Response.LOCATIONS_PAGE_1)
        assert(allLocations.infoModel != null)
        assert(allLocations.infoModel?.nextPage == "https://rickandmortyapi.com/api/location/?page=2"
                && allLocations.infoModel?.previousPage == null)
        assert(allLocations.results.size == 20)
        assert(allLocations.infoModel?.pages != 0)
        assert(allLocations.infoModel?.count != 0)

        allLocations = MockResponseReader.mockGetAllLocations(MockResponseReader.Response.LOCATIONS_PAGE_2)
        assert(allLocations.infoModel?.nextPage == "https://rickandmortyapi.com/api/location/?page=3"
                && allLocations.infoModel?.previousPage != null)
        assert(allLocations.results.size == 20)
        assert(allLocations.infoModel?.pages != 0)
        assert(allLocations.infoModel?.count != 0)
    }


    @Test
    fun multipleCharacterResponse(){
        multipleLocation = MockResponseReader.mockGetMultipleLocations(MockResponseReader.Response.MULTIPLE_LOCATIONS)
        multipleLocation.forEach {
            assert(it.id != 0)
            assert(it.name.isNotEmpty())
            assert(it.dimension.isNotEmpty())
            assert(it.type.isNotEmpty())
            assert(it.residents.isNotEmpty())
            assert(it.url.isNotEmpty())
        }
    }

    @Test
    fun singleCharacterResponse(){
        singleLocation = MockResponseReader.mockGetSingleLocation(MockResponseReader.Response.SINGLE_LOCATION)

        assert(singleLocation.id != 0)
        assert(singleLocation.name.isNotEmpty())
        assert(singleLocation.dimension.isNotEmpty())
        assert(singleLocation.type.isNotEmpty())
        assert(singleLocation.residents.isNotEmpty())
        assert(singleLocation.url.isNotEmpty())
    }

}