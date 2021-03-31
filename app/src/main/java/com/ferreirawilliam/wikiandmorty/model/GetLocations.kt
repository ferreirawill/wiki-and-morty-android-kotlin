package com.ferreirawilliam.wikiandmorty.model

import com.google.gson.annotations.SerializedName

data class GetLocations(
        @SerializedName("info")
        var infoModel: ResponseInfoModel?,
        @SerializedName("results")
        var results:List<LocationsModel>
)