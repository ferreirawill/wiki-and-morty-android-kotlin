package com.ferreirawilliam.wikiandmorty.view.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.view.adapters.CharactersAdapter
import com.ferreirawilliam.wikiandmorty.viewmodel.CharactersViewModel

class CharactersFragment : Fragment() {

    private lateinit var charactersViewModel: CharactersViewModel
    private val mAdapter = CharactersAdapter()


    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        charactersViewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_characters, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_all_characters)
        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = mAdapter

        observer()

        charactersViewModel.loadAllCharacters()

        return root
    }


    private fun observer(){
        charactersViewModel.characterList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)

        })
    }

}