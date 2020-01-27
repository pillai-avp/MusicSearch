package com.avp.musicsearch.dto

import com.google.gson.annotations.SerializedName

data class GenericSearchResponse(
    @SerializedName("data")
    val searchData: List<SearchData>,
    val next: String,
    val total: Int
)