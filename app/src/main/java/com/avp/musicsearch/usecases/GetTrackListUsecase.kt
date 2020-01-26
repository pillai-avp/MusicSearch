package com.avp.musicsearch.usecases

import com.avp.musicsearch.dto.TrackData
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
class GetTrackListUsecase(
    scope: CoroutineScope,
    private val repository: AlbumRepository
) : UseCase<String, List<TrackData>>(scope) {

    override suspend fun execute(parameters: String): List<TrackData> {
        return repository.getTrackList(parameters).tracks
    }
}