package com.avp.musicsearch.usecases

import com.avp.musicsearch.common.UseCase
import com.avp.musicsearch.dto.Artist
import com.avp.musicsearch.repo.AlbumRepository
import kotlinx.coroutines.CoroutineScope


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 22 January 2020
 */
class SearchArtistUseCase(
    scope: CoroutineScope,
    private val repository: AlbumRepository
) : UseCase<String, List<Artist>>(scope) {

    override suspend fun execute(parameters: String): List<Artist> {
        return repository.genericArtistSearch(parameters).artists
    }
}