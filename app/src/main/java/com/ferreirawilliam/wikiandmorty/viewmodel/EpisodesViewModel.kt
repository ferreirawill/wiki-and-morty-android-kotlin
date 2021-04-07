package com.ferreirawilliam.wikiandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.model.EpisodesModel
import com.ferreirawilliam.wikiandmorty.model.GetEpisodes
import com.ferreirawilliam.wikiandmorty.model.ResponseInfoModel
import com.ferreirawilliam.wikiandmorty.services.listeners.EpisodesListener
import com.ferreirawilliam.wikiandmorty.services.repository.CharacterRepository
import com.ferreirawilliam.wikiandmorty.services.repository.EpisodesRepository

class EpisodesViewModel : ViewModel() {

    private val mEpisodesRepository = EpisodesRepository()

    private val _episode = MutableLiveData<EpisodesModel>()
    val episodesModel:LiveData<EpisodesModel> = _episode

    private val _episodeList = MutableLiveData<List<EpisodesModel>>()
    val episodeList:LiveData<List<EpisodesModel>> = _episodeList

    private lateinit var _responseInfo: ResponseInfoModel

    fun loadAllEpisodes(){
        mEpisodesRepository.getAll(object : EpisodesListener{
            override fun onSuccess(model: GetEpisodes) {
                Log.d("EpisodesViewModel", "onSuccess: ${model.results[0]}")
            }

            override fun onFailure(string: String) {
                Log.d("EpisodesViewModel", "onFailure: $string")
            }

        })
    }
}