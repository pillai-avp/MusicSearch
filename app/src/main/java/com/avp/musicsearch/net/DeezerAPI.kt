package com.avp.musicsearch.net

import com.avp.musicsearch.common.DEEZER_ACCESS_TOKEN
import com.avp.musicsearch.dto.ArtistSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 22 January 2020
 */
interface DeezerAPI {
    @GET("/search/artist")
    fun searchArtist(@Query("q") query: String, @Header("access_token") header: String = DEEZER_ACCESS_TOKEN): Call<ArtistSearchResponse>
}