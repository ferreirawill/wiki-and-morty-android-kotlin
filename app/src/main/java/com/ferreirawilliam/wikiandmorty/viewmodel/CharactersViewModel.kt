package com.ferreirawilliam.wikiandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import com.ferreirawilliam.wikiandmorty.model.ResponseInfoModel
import com.ferreirawilliam.wikiandmorty.services.listeners.CharacterListener
import com.ferreirawilliam.wikiandmorty.services.repository.CharacterRepository

class CharactersViewModel : ViewModel() {

    private val mCharacterRepository = CharacterRepository()

    private val _character = MutableLiveData<CharacterModel>()
    val character:LiveData<CharacterModel> = _character

    private val _characterList = MutableLiveData<List<CharacterModel>>()
    val characterList:LiveData<List<CharacterModel>> = _characterList

    private lateinit var _responseInfo: ResponseInfoModel

    fun loadAllCharacters(){
        mCharacterRepository.getAll(object : CharacterListener {
            override fun onSuccess(model: GetCharacters) {
                 _responseInfo = model.infoModel!!
                _characterList.value = model.results
            }

            override fun onFailure(string: String) {
                Log.d("API REQUEST", "onFailure: $string")
            }

        })
    }

    fun loadSingleCharacter(id: Int){
        mCharacterRepository.getSingle(id,object :CharacterListener{
            override fun onSuccess(model: GetCharacters) {

            }

            override fun onFailure(string: String) {

            }

        })
    }

    fun loadNewPage():Boolean {
        if (_responseInfo.nextPage == null) return false
        _responseInfo.nextPage?.let {
            mCharacterRepository.getNewPage(it,object : CharacterListener{
            override fun onSuccess(model: GetCharacters) {
                _responseInfo = model.infoModel!!

                _characterList.value = characterList.value?.plus(model.results) ?: characterList.value
            }

            override fun onFailure(string: String) {
                Log.d("API REQUEST", "onFailure: $string")
            }
        })
        }
        return true
    }

    fun getCharacterFromIndex(index:Int):CharacterModel{
         return characterList.value?.get(index) ?: CharacterModel.VOID_CHARACTER
    }

}