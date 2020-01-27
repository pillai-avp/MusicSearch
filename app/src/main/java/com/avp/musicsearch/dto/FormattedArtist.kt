package com.avp.musicsearch.dto


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: pillai.avp@gmail.com
 *
 * Date: 27 January 2020
 */
data class FormattedArtist(
    val artist: Artist,
    val listAlbums: List<Album>
)