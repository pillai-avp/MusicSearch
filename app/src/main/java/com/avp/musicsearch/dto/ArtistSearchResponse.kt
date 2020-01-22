package com.avp.musicsearch.dto

import com.google.gson.annotations.SerializedName

data class ArtistSearchResponse(
    @SerializedName("data")
    val artists: List<Artist>,
    val next: String,
    val total: Int
)