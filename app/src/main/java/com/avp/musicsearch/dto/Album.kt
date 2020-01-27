package com.avp.musicsearch.dto

data class Album(
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val id: Int,
    val title: String,
    val tracklist: String,
    val type: String
)