package com.ferreirawilliam.wikiandmorty.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.model.GetCharacters
import com.ferreirawilliam.wikiandmorty.services.listeners.CharacterListener
import com.ferreirawilliam.wikiandmorty.services.repository.CharacterRepository

class CharactersViewModel : ViewModel() {

    private val mCharacterRepository = CharacterRepository()

    private val _character = MutableLiveData<CharacterModel>()
    val character:LiveData<CharacterModel> = _character

    private val _characterList = MutableLiveData<List<CharacterModel>>()
    val characterList:LiveData<List<CharacterModel>> = _characterList


    fun loadAllCharacters(){
        mCharacterRepository.getAllCharacters(object : CharacterListener {
            override fun onSuccess(model: GetCharacters) {
                _characterList.value = model.results
            }

            override fun onFailure(string: String) {
                Log.d("API REQUEST", "onFailure: $string")
            }

        })
    }


    fun loadSingleCharacter(id: Int){
        mCharacterRepository.getSingleCharacters(id,object :CharacterListener{
            override fun onSuccess(model: GetCharacters) {
                Log.d("API REQUEST", "onSuccess info: ${model.infoModel}")
                Log.d("API REQUEST", "onSuccess result: ${model.results[0]}")
            }

            override fun onFailure(string: String) {
                Log.d("API REQUEST", "onSuccess info: $string")
                Log.d("API REQUEST", "onSuccess result: $string")
            }

        })
    }

}