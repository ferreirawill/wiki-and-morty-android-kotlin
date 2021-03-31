package com.ferreirawilliam.wikiandmorty.view.viewHolders

import android.view.View
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import kotlinx.android.synthetic.main.character_card.view.*

class CharactersViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    fun bind(character:CharacterModel){
        val characterName = itemView.findViewById<TextView>(R.id.text_character_name)

        characterName.text = character.name

    }

}