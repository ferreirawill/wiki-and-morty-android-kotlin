package com.ferreirawilliam.wikiandmorty.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.view.adapters.LocationsAdapter
import com.ferreirawilliam.wikiandmorty.viewmodel.LocationsViewModel

class LocationsFragment : Fragment() {

    private lateinit var locationsViewModel: LocationsViewModel
    private lateinit var mAdapter: LocationsAdapter


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        locationsViewModel =
                ViewModelProvider(this).get(LocationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_locations, container, false)

        locationsViewModel.loadAll()

        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_all_locations)

        recyclerView.layoutManager = LinearLayoutManager(context)

        mAdapter = LocationsAdapter()







        return root
    }
}