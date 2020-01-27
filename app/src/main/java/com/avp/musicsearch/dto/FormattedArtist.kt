package com.avp.musicsearch.dto


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 27 January 2020
 */
data class FormattedArtist(
    val artist: Artist,
    val listAlbums: List<Album>
)