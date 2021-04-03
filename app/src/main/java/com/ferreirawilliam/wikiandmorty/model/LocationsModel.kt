package com.ferreirawilliam.wikiandmorty.model

import com.google.gson.annotations.SerializedName

data class LocationsModel(
        @SerializedName("id")
        var id:Int,
        @SerializedName("name")
        var name:String,
        @SerializedName("type")
        var type:String,
        @SerializedName("dimension")
        var dimension:String,
        @SerializedName("residents")
        var residents:List<String>,
        @SerializedName("url")
        var url:String,
        @SerializedName("created")
        var created:String
)