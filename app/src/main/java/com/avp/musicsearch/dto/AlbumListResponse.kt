package com.avp.musicsearch.dto

import com.google.gson.annotations.SerializedName

data class AlbumListResponse(
    @SerializedName("data")
    val albums: List<Album>,
    val next: String,
    val total: Int
)