package com.avp.musicsearch.dto

import com.google.gson.annotations.SerializedName

data class TrackListResponse(
    @SerializedName("data")
    val tracks: List<TrackData>,
    val total: Int
)