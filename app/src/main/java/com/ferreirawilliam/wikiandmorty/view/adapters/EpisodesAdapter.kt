package com.ferreirawilliam.wikiandmorty.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.model.EpisodesModel
import com.ferreirawilliam.wikiandmorty.model.LocationsModel
import com.ferreirawilliam.wikiandmorty.view.viewHolders.EpisodesViewHolder

class EpisodesAdapter:RecyclerView.Adapter<EpisodesViewHolder>() {

    private var mEpisodesList = listOf<EpisodesModel>()
    private var mEpisodePosition = 0


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EpisodesViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.episode_card,parent,false)
        return EpisodesViewHolder(item)
    }

    override fun getItemCount(): Int {
        return mEpisodesList.count()
    }

    override fun onBindViewHolder(holder: EpisodesViewHolder, position: Int) {
        holder.bind(mEpisodesList[position])
        mEpisodePosition = position
    }

    fun getItemPosition():Int{
        return mEpisodePosition
    }

    fun updateList(episodeList:List<EpisodesModel>){
        mEpisodesList = episodeList
        notifyDataSetChanged()
    }


}