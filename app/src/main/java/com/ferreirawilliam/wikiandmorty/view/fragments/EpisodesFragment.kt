package com.ferreirawilliam.wikiandmorty.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.viewmodel.EpisodesViewModel

class EpisodesFragment : Fragment() {

    private lateinit var episodesViewModel: EpisodesViewModel

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        episodesViewModel =
                ViewModelProvider(this).get(EpisodesViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_slideshow, container, false)
        val textView: TextView = root.findViewById(R.id.text_slideshow)
        episodesViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}