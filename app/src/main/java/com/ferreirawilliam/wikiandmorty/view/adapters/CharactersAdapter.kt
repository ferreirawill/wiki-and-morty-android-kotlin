package com.ferreirawilliam.wikiandmorty.view.adapters


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.view.viewHolders.CharactersViewHolder


class CharactersAdapter:RecyclerView.Adapter<CharactersViewHolder>() {
    private var mCharactersList: List<CharacterModel> = arrayListOf()
    private var actualPosition:Int = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CharactersViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.character_card,parent,false)
        return CharactersViewHolder(item)
    }

    override fun getItemCount(): Int {
       return mCharactersList.count()
    }

    override fun onBindViewHolder(holder: CharactersViewHolder, position: Int) {
        holder.bind(mCharactersList[position])
        actualPosition = position
    }

    fun updateList(list: List<CharacterModel>){
        mCharactersList = list
        notifyDataSetChanged()
    }

    fun getItemPosition():Int {
        return actualPosition
    }

}