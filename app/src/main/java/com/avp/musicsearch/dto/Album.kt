package com.avp.musicsearch.dto

data class Album(
    val artist: Artist,
    val cover: String,
    val cover_big: String,
    val cover_medium: String,
    val cover_small: String,
    val cover_xl: String,
    val explicit_lyrics: Boolean,
    val genre_id: Int,
    val id: Int,
    val link: String,
    val nb_tracks: Int,
    val record_type: String,
    val title: String,
    val tracklist: String,
    val type: String
)