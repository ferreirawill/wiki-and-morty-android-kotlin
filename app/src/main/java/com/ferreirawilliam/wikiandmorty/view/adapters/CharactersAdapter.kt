package com.ferreirawilliam.wikiandmorty.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.view.viewHolders.CharactersViewHolder
import kotlinx.android.synthetic.main.character_card.view.*

class CharactersAdapter:RecyclerView.Adapter<CharactersViewHolder>() {
    private var mCharactersList: List<CharacterModel> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.character_card,parent,false)
        return CharactersViewHolder(item)
    }

    override fun getItemCount(): Int {
       return mCharactersList.count()
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(mCharactersList[position])
    }

    fun updateList(list: List<CharacterModel>){
        mCharactersList = list
        notifyDataSetChanged()
    }

}