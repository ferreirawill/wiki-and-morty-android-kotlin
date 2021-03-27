package com.ferreirawilliam.wikiandmorty.model

import com.google.gson.annotations.SerializedName

data class ResponseInfo(
    @SerializedName("count")
    var count:Int,
    @SerializedName("pages")
    var pages:Int,
    @SerializedName("next")
    var nextPage:String,
    @SerializedName("prev")
    var previousPage:String?
)