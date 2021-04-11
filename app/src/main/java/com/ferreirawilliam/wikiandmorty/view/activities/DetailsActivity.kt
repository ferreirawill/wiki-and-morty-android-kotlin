package com.ferreirawilliam.wikiandmorty.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
        setSupportActionBar(findViewById(R.id.toolbar_details))
        val collapsingToolbarLayout = findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar_layout_details)
        val appToolbarLayout = findViewById<AppBarLayout>(R.id.app_bar_details)
        val characterImage = findViewById<ImageView>(R.id.image_character_details)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        val intentExtras = intent.extras

        var characterModel:CharacterModel = intentExtras?.getSerializable(CharacterModel.SERIALIZED_OBJECT) as CharacterModel


        Picasso.get().load(characterModel.imageUrl).into(characterImage)




        collapsingToolbarLayout.title = characterModel.name


    }
}