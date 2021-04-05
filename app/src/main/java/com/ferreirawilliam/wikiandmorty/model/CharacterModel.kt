package com.ferreirawilliam.wikiandmorty.model

import android.graphics.Bitmap
import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class CharacterModel(
    @SerializedName("id")
    var id:Int,
    @SerializedName("name")
    var name:String,
    @SerializedName("status")
    var status: String,
    @SerializedName("species")
    var species:String,
    @SerializedName("type")
    var type: String,
    @SerializedName("gender")
    var gender: String,
    @SerializedName("origin")
    var origin: Map<String,String>,
    @SerializedName("location")
    var location:Map<String,String>,
    @SerializedName("image")
    var imageUrl:String,
    @SerializedName("episode")
    var episode:List<String>,
    @SerializedName("url")
    var url:String,
    @SerializedName("created")
    var created:String
    ):Serializable {

    companion object{
        const val SERIALIZED_OBJECT = "serializedObject"
        val VOID_CHARACTER = CharacterModel(
            0,
            "",
            "","","","", mapOf(), mapOf(),"", arrayListOf(),"","")
    }

    object CONSTANTS {
        const val ALIVE = "Alive"
        const val DEAD = "Dead"
        const val UNKNOW = "unknown"
        const val NAME = "name"
        const val URL ="url"

    }
}
