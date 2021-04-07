package com.ferreirawilliam.wikiandmorty.view.fragments

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.viewmodel.CharacterDetailsViewModel

class CharacterDetailsFragment : Fragment() {

    companion object {
        fun newInstance() = CharacterDetailsFragment()
    }

    private lateinit var viewModel: CharacterDetailsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.character_details_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(CharacterDetailsViewModel::class.java)
        // TODO: Use the ViewModel
    }

}