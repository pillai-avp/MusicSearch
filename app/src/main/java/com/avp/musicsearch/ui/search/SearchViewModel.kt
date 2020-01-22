package com.avp.musicsearch.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.avp.musicsearch.common.Either
import com.avp.musicsearch.common.map
import com.avp.musicsearch.common.succeeded
import com.avp.musicsearch.dto.Artist
import com.avp.musicsearch.ui.abstract.BaseViewModel
import com.avp.musicsearch.usecases.SearchArtistUseCase
import kotlinx.coroutines.CoroutineScope
import timber.log.Timber


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 21 January 2020
 */
class SearchViewModel(
    private val scope: CoroutineScope,
    private val searchArtistUseCase: SearchArtistUseCase
)

    : BaseViewModel(scope) {

    fun doSearch(query: String): LiveData<List<Artist>?> {
        val searchResult = MutableLiveData<Either<List<Artist>>>()
        searchArtistUseCase(query, searchResult)
        return searchResult.map { either ->
            if ((either.succeeded)) {
                (either as Either.Success).data
            } else {
                Timber.e((either as Either.Error).exception)
                null
            }
        }

    }



}