package com.ferreirawilliam.wikiandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferreirawilliam.wikiandmorty.model.EpisodesModel
import com.ferreirawilliam.wikiandmorty.model.GetLocations
import com.ferreirawilliam.wikiandmorty.model.LocationsModel
import com.ferreirawilliam.wikiandmorty.model.ResponseInfoModel
import com.ferreirawilliam.wikiandmorty.services.listeners.LocationsListener

import com.ferreirawilliam.wikiandmorty.services.repository.LocationsRepository

class LocationsViewModel : ViewModel() {


    private val mLocationsRepository = LocationsRepository()

    private val _location = MutableLiveData<LocationsModel>()
    val locationModel:LiveData<LocationsModel> = _location

    private val _locationList = MutableLiveData<List<LocationsModel>>()
    val locationList:LiveData<List<LocationsModel>> = _locationList

    private lateinit var _responseInfo: ResponseInfoModel

    fun loadAll(){
        mLocationsRepository.getAll(object :LocationsListener{
            override fun onSuccess(model: GetLocations) {
                Log.d("LocationsViewModel", "onSuccess: ${model.results[0]}")
            }

            override fun onFailure(string: String) {
                Log.d("LocationsViewModel", "onSuccess: $string")
            }

        })
    }

}