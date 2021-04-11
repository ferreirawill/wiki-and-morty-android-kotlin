package com.ferreirawilliam.wikiandmorty.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.view.adapters.EpisodesAdapter
import com.ferreirawilliam.wikiandmorty.viewmodel.EpisodesViewModel

class EpisodesFragment : Fragment() {

    private lateinit var episodesViewModel: EpisodesViewModel
    private lateinit var mRecycler: RecyclerView
    private lateinit var mAdapter: EpisodesAdapter
    private lateinit var mCircularProgressIndicator: ProgressBar


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        episodesViewModel =
                ViewModelProvider(this).get(EpisodesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_episodes, container, false)

        episodesViewModel.loadAllEpisodes()

        mRecycler = root.findViewById(R.id.recycler_all_episodes)
        mCircularProgressIndicator = root.findViewById(R.id.progress_circular_episodes)
        mCircularProgressIndicator.visibility = View.INVISIBLE
        mRecycler.layoutManager = LinearLayoutManager(context)

        mAdapter = EpisodesAdapter()

        mRecycler.adapter = mAdapter

        mRecycler.onScrollStateChanged(RecyclerView.SCROLL_STATE_SETTLING)
        mRecycler.onScrollStateChanged(RecyclerView.SCROLL_STATE_DRAGGING)

        setListeners()
        setObservers()


        return root
    }

    private fun setObservers() {
        episodesViewModel.episodeList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
            mCircularProgressIndicator.visibility = View.INVISIBLE
        })
    }

    private fun setListeners() {
        mRecycler.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if(newState == RecyclerView.SCROLL_STATE_SETTLING || newState == RecyclerView.SCROLL_STATE_DRAGGING){
                    if(mAdapter.getItemPosition() == mAdapter.itemCount -1 ){
                        mCircularProgressIndicator.visibility = View.VISIBLE
                        val pageIsNotNull = episodesViewModel.loadNewPage()
                        if (pageIsNotNull)
                            mCircularProgressIndicator.visibility = View.VISIBLE
                        else
                            mCircularProgressIndicator.visibility = View.INVISIBLE

                    }
                }
            }
        })
    }
}