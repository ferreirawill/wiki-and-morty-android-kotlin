package com.ferreirawilliam.wikiandmorty.viewmodel

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import org.robolectric.annotation.Config

@RunWith(RobolectricTestRunner::class)
@Config(manifest = Config.NONE)
class LocationsViewModelTest {

    private lateinit var locationsViewModel: LocationsViewModel



    @Before
    fun setUpTests(){
        locationsViewModel = LocationsViewModel()
    }

    @Test
    fun viewmodelInstance(){
        assert(::locationsViewModel.isInitialized)

    }


}