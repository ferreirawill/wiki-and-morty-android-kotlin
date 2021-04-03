package com.ferreirawilliam.wikiandmorty.view.viewHolders

import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.character_card.view.*

class CharactersViewHolder(itemView: View):RecyclerView.ViewHolder(itemView) {

    fun bind(character:CharacterModel){
        setName("${character.id}: ${character.name}")
        setAvatar(character.imageUrl)
        setStatusAndType("${character.status} - ${character.species} ")
        setLastLocation("${character.location["name"]}")
        setOrigin("${character.origin["name"]}")

    }

    private fun setName(characterName:String){
        val textCharacterName = itemView.findViewById<TextView>(R.id.text_character_name)
        textCharacterName.text = characterName

    }
    private fun setAvatar(imageUrl:String){
        val imageCharacterAvatar = itemView.findViewById<ImageView>(R.id.image_character_avatar)
        Picasso.get().load(imageUrl).into(imageCharacterAvatar)
    }
    private fun setStatusAndType(status:String){
        val textCharacterType = itemView.findViewById<TextView>(R.id.text_character_status)
        textCharacterType.text = status
    }
    private fun setLastLocation(lastLocation:String){
        val textCharacterLocation = itemView.findViewById<TextView>(R.id.text_character_location)
        textCharacterLocation.text = lastLocation
    }
    private fun setOrigin(origin:String){
        val textCharacterOrigin = itemView.findViewById<TextView>(R.id.text_character_origin)
        textCharacterOrigin.text = origin
    }
}