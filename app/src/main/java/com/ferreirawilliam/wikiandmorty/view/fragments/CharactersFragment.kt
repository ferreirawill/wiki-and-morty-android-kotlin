package com.ferreirawilliam.wikiandmorty.view.fragments


import android.content.Intent
import android.os.Bundle
import android.util.Log

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.ferreirawilliam.wikiandmorty.view.activities.DetailsActivity
import com.ferreirawilliam.wikiandmorty.view.adapters.CharactersAdapter
import com.ferreirawilliam.wikiandmorty.view.listeners.OnMoreListener
import com.ferreirawilliam.wikiandmorty.viewmodel.CharactersViewModel


class CharactersFragment : Fragment(),OnMoreListener {
    private lateinit var charactersViewModel: CharactersViewModel
    private lateinit var mAdapter:CharactersAdapter
    private lateinit var mCircularProgressIndicator:ProgressBar



    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?): View? {

        charactersViewModel = ViewModelProvider(this).get(CharactersViewModel::class.java)

        val root = inflater.inflate(R.layout.fragment_characters, container, false)

        val recyclerView = root.findViewById<RecyclerView>(R.id.recycler_all_characters)
        mCircularProgressIndicator = root.findViewById(R.id.progress_circular_characters)
        mCircularProgressIndicator.visibility = View.INVISIBLE


        mAdapter = CharactersAdapter(this)



        recyclerView.layoutManager = LinearLayoutManager(context)

        recyclerView.adapter = mAdapter

        observer()




        charactersViewModel.loadAllCharacters()

        recyclerView.onScrollStateChanged(RecyclerView.SCROLL_STATE_SETTLING)
        recyclerView.onScrollStateChanged(RecyclerView.SCROLL_STATE_DRAGGING)

        recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)

                if (newState == RecyclerView.SCROLL_STATE_SETTLING || newState ==RecyclerView.SCROLL_STATE_DRAGGING){
                    if(mAdapter.getItemPosition() == (mAdapter.itemCount -1)){

                        mCircularProgressIndicator.visibility = View.VISIBLE
                        val pageIsNotNull = charactersViewModel.loadNewPage()
                        if (pageIsNotNull)
                            mCircularProgressIndicator.visibility = View.VISIBLE
                        else
                            mCircularProgressIndicator.visibility = View.INVISIBLE
                    }
                }

            }
        })



        return root
    }


    private fun observer(){
        charactersViewModel.characterList.observe(viewLifecycleOwner, Observer {
            mAdapter.updateList(it)
            mCircularProgressIndicator.visibility = View.INVISIBLE
        })
    }

    override fun onMoreClick(adapterPosition: Int) {
        Log.d("CHARACTERS FRAGMENT", "onMoreClick: $adapterPosition")

        val intent = Intent(context,DetailsActivity::class.java)
        intent.putExtra(CharacterModel.SERIALIZED_OBJECT,charactersViewModel.getCharacterFromIndex(adapterPosition))

        startActivity(intent)
    }

}