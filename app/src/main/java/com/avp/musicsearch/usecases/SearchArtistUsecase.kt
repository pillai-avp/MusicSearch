package com.avp.musicsearch.usecases

import com.avp.musicsearch.dto.FormattedArtist
import com.avp.musicsearch.dto.GenericSearchResponse
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
) : UseCase<String, List<FormattedArtist>>(scope) {

    override suspend fun execute(parameters: String): List<FormattedArtist> {

        return groupArtistsByID(repository.genericArtistSearch(parameters))
    }

    private fun groupArtistsByID(genericArtistSearch: GenericSearchResponse) =
        genericArtistSearch.searchData.groupBy { groupedSearchData ->
            groupedSearchData.artist.id
        }.values.mapNotNull {
            FormattedArtist(it[0].artist, it.map { searchData -> searchData.album })
        }


}