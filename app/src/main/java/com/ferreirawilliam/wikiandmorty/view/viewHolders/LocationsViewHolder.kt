package com.ferreirawilliam.wikiandmorty.view.viewHolders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.model.LocationsModel

class LocationsViewHolder(itemView:View): RecyclerView.ViewHolder(itemView) {

    fun bind(locationModel: LocationsModel){
        setName(locationModel.name)
        setType(locationModel.type)
        setDimension(locationModel.dimension)
        setNumberOfResidents(locationModel.residents.count())
    }

    private fun setDimension(dimension: String) {
        val itemName = itemView.findViewById<TextView>(R.id.text_location_dimension)
        itemName.text = dimension
    }

    private fun setType(type: String) {
        val itemName = itemView.findViewById<TextView>(R.id.text_location_type)
        itemName.text = type
    }

    private fun setName(name:String) {
        val itemName = itemView.findViewById<TextView>(R.id.text_location_name)
        itemName.text = name
    }

    private fun setNumberOfResidents(residents: Int) {
        val itemName = itemView.findViewById<TextView>(R.id.text_location_number_of_residents)
        itemName.text = residents.toString()
    }
}