package com.ferreirawilliam.wikiandmorty.model

import com.google.gson.annotations.SerializedName

data class EpisodesModel (
        @SerializedName("id")
        var id:Int,
        @SerializedName("name")
        var name:String,
        @SerializedName("air_date")
        var airDate:String,
        @SerializedName("episode")
        var episode:String,
        @SerializedName("characters")
        var characters:List<String>,
        @SerializedName("url")
        var url:String,
        @SerializedName("created")
        var created:String
)