package com.ferreirawilliam.wikiandmorty.model
import com.google.gson.annotations.SerializedName

data class GetCharacters(
    @SerializedName("info")
    var info: ResponseInfo,
    @SerializedName("results")
    var results:List<CharacterResult>
)