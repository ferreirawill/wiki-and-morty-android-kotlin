package com.ferreirawilliam.wikiandmorty.model

import com.google.gson.annotations.SerializedName

data class CharacterResult(
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
    var image:String,
    @SerializedName("episode")
    var episode:List<String>,
    @SerializedName("url")
    var url:String,
    @SerializedName("created")
    var created:String
    )