package com.avp.musicsearch.repo

import com.avp.musicsearch.dto.AlbumListResponse
import com.avp.musicsearch.dto.ArtistSearchResponse
import com.avp.musicsearch.dto.TrackListResponse
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
    suspend fun getAlbumsForArtist(artistName: String): AlbumListResponse
    suspend fun getTrackList(url : String) : TrackListResponse
}

class AlbumRepositoryImpl(private val deezerAPI: DeezerAPI) : AlbumRepository {

    override suspend fun genericArtistSearch(query: String): ArtistSearchResponse
            = deezerAPI.searchArtist(query).await()

    override suspend fun getAlbumsForArtist(artistName: String): AlbumListResponse
            = deezerAPI.albumList(artistName).await()

    override suspend fun getTrackList(url: String): TrackListResponse
            = deezerAPI.getTrackList(url).await()
    }
