package com.ferreirawilliam.wikiandmorty.view.viewHolders

import android.view.View
import android.widget.Button

import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView

import androidx.recyclerview.widget.RecyclerView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.view.listeners.OnMoreListener

import com.squareup.picasso.Picasso

class CharactersViewHolder(itemView: View,onMoreListener: OnMoreListener):RecyclerView.ViewHolder(itemView) {
    private val buttonListener = onMoreListener


    fun bind(character:CharacterModel){
        setName("${character.name}")
        setAvatar(character.imageUrl)
        setStatusAndSpecie(status = character.status,specie = character.species)
        setLastLocation("${character.location[CharacterModel.CONSTANTS.NAME]}")
        setOrigin("${character.origin[CharacterModel.CONSTANTS.NAME]}")
        setButtonListener()
    }


    private fun setButtonListener(){
        val buttonMore = itemView.findViewById<Button>(R.id.button_more)
        buttonMore.setOnClickListener {
            buttonListener.onMoreClick(adapterPosition)
        }
    }

    private fun setName(characterName:String){
        val textCharacterName = itemView.findViewById<TextView>(R.id.text_character_name)
        textCharacterName.text = characterName

    }
    private fun setAvatar(imageUrl:String){
        val imageCharacterAvatar = itemView.findViewById<ImageView>(R.id.image_character_avatar)
        Picasso.get().load(imageUrl).into(imageCharacterAvatar)
    }
    private fun setStatusAndSpecie(status:String,specie:String){
        val statusCircle = itemView.findViewById<LinearLayout>(R.id.status_circle)
        val textCharacterType = itemView.findViewById<TextView>(R.id.text_character_status)

        when(status){
            CharacterModel.CONSTANTS.ALIVE -> statusCircle.setBackgroundResource(R.drawable.status_circle_alive)
            CharacterModel.CONSTANTS.DEAD -> statusCircle.setBackgroundResource(R.drawable.status_circle_dead)
            CharacterModel.CONSTANTS.UNKNOW -> statusCircle.setBackgroundResource(R.drawable.status_circle_unknow)
        }

        textCharacterType.text = " $status - $specie "
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