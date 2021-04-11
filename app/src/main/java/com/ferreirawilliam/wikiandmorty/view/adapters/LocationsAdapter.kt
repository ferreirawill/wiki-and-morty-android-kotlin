package com.ferreirawilliam.wikiandmorty.view.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.model.LocationsModel
import com.ferreirawilliam.wikiandmorty.view.viewHolders.LocationsViewHolder
import kotlinx.android.synthetic.main.episode_card.view.*

class LocationsAdapter: RecyclerView.Adapter<LocationsViewHolder>() {
    private var mLocationsList = listOf<LocationsModel>()
    private var itemPosition:Int = 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LocationsViewHolder {
        val item = LayoutInflater.from(parent.context).inflate(R.layout.location_card,parent,false)
        return LocationsViewHolder(item)
    }

    override fun getItemCount(): Int {
        return mLocationsList.count()
    }

    override fun onBindViewHolder(holder: LocationsViewHolder, position: Int) {
        holder.bind(mLocationsList[position])
        itemPosition = position
    }

    fun getItemPosition(): Int {
        return itemPosition
    }

    fun updateList(list: List<LocationsModel>){
        mLocationsList = list

        notifyDataSetChanged()
    }

}