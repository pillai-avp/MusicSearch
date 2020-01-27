package com.avp.musicsearch.net

import com.avp.musicsearch.common.DEEZER_ACCESS_TOKEN
import com.avp.musicsearch.dto.AlbumListResponse
import com.avp.musicsearch.dto.GenericSearchResponse
import com.avp.musicsearch.dto.TrackListResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query
import retrofit2.http.Url


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: pillai.avp@gmail.com
 *
 * Date: 22 January 2020
 */
interface DeezerAPI {


    @GET("/search/album")
    fun albumList(@Query("q") query: String, @Header("access_token") header: String = DEEZER_ACCESS_TOKEN): Call<AlbumListResponse>

    @GET("/search")
    fun searchArtist(@Query("q") query: String, @Header("access_token") header: String = DEEZER_ACCESS_TOKEN): Call<GenericSearchResponse>

    @GET
    fun getTrackList(@Url url: String, @Header("access_token") header: String = DEEZER_ACCESS_TOKEN): Call<TrackListResponse>
}