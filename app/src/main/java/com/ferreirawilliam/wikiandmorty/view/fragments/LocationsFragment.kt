package com.ferreirawilliam.wikiandmorty.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.ProgressBar
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
    private lateinit var recyclerView: RecyclerView
    private lateinit var mCircularProgressIndicator: ProgressBar


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        locationsViewModel =
                ViewModelProvider(this).get(LocationsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_locations, container, false)

        locationsViewModel.loadAll()

        recyclerView = root.findViewById(R.id.recycler_all_locations)
        mCircularProgressIndicator = root.findViewById(R.id.progress_circular_locations)
        mCircularProgressIndicator.visibility = View.INVISIBLE

        recyclerView.layoutManager = LinearLayoutManager(context)

        mAdapter = LocationsAdapter()
        recyclerView.adapter = mAdapter

        recyclerView.onScrollStateChanged(RecyclerView.SCROLL_STATE_SETTLING)
        recyclerView.onScrollStateChanged(RecyclerView.SCROLL_STATE_DRAGGING)


        setListeners()
        setObservers()





        return root
    }


    private fun setListeners(){
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_SETTLING || newState == RecyclerView.SCROLL_STATE_DRAGGING){
                    if(mAdapter.getItemPosition() == mAdapter.itemCount -1){

                        val pageIsNotNull = locationsViewModel.loadNewPage()
                        if (pageIsNotNull)
                            mCircularProgressIndicator.visibility = View.VISIBLE
                        else
                            mCircularProgressIndicator.visibility = View.INVISIBLE
                    }
                }
            }
        })
    }

    private fun setObservers(){
        locationsViewModel.locationList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
            mCircularProgressIndicator.visibility = View.INVISIBLE
        })


    }

}