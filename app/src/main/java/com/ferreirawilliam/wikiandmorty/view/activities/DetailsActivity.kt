package com.ferreirawilliam.wikiandmorty.view.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.ferreirawilliam.wikiandmorty.R
import com.ferreirawilliam.wikiandmorty.model.CharacterModel
import kotlinx.android.synthetic.main.activity_details.*

class DetailsActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val textName = findViewById<TextView>(R.id.text_extras_name)
        val intentExtras = intent.extras

        var characterModel:CharacterModel = intentExtras?.getSerializable(CharacterModel.SERIALIZED_OBJECT) as CharacterModel

        textName.text = characterModel.name

    }
}