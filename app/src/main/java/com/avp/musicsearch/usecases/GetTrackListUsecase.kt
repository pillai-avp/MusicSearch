package com.avp.musicsearch.usecases

import com.avp.musicsearch.dto.TrackData
import com.avp.musicsearch.repo.AlbumRepository
import kotlinx.coroutines.CoroutineScope


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: pillai.avp@gmail.com
 *
 * Date: 22 January 2020
 */
class GetTrackListUsecase(
    scope: CoroutineScope,
    private val repository: AlbumRepository
) : UseCase<String, Map<Int, List<TrackData>>>(scope) {

    override suspend fun execute(parameters: String): Map<Int, List<TrackData>> {
        return repository.getTrackList(parameters).tracks.groupBy { it.disk_number }
    }
}