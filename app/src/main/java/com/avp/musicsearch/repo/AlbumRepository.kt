package com.avp.musicsearch.repo

import com.avp.musicsearch.dto.ArtistSearchResponse
import com.avp.musicsearch.net.DeezerAPI
import ru.gildor.coroutines.retrofit.await


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 21 January 2020
 */

interface AlbumRepository {
    suspend fun genericArtistSearch(query: String): ArtistSearchResponse
}

class AlbumRepositoryImpl(private val deezerAPI: DeezerAPI) : AlbumRepository {

    override suspend fun genericArtistSearch(query: String): ArtistSearchResponse {
        return deezerAPI.searchArtist(query).await()
    }

}