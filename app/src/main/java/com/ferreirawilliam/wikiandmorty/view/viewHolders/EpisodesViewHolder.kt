package com.ferreirawilliam.wikiandmorty.view.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.model.EpisodesModel
import kotlinx.android.synthetic.main.episode_card.view.*
import org.w3c.dom.Text

class EpisodesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(episodeModel: EpisodesModel){
        setName(episodeModel.name)
        setEpisode(episodeModel.episode)
        setAirDate(episodeModel.airDate)
        setCharacters(episodeModel.characters.count())
    }

    private fun setCharacters(numberOfCharacters: Int) {
        val textCharactersNumber = itemView.findViewById<TextView>(R.id.text_episode_number_of_characters)
        textCharactersNumber.text = numberOfCharacters.toString()

    }

    private fun setAirDate(airDate: String) {
        val textAirDate = itemView.findViewById<TextView>(R.id.text_episode_air_date)
        textAirDate.text = airDate

    }

    private fun setEpisode(episode: String) {
        val textEpisodeNumber = itemView.findViewById<TextView>(R.id.text_episode_number)
        textEpisodeNumber.text = episode
    }

    private fun setName(name: String) {
        val textName = itemView.findViewById<TextView>(R.id.text_episode_name)
        textName.text = name
    }

}