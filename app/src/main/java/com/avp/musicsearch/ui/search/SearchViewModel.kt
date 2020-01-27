package com.avp.musicsearch.ui.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.avp.musicsearch.common.Either
import com.avp.musicsearch.common.Event
import com.avp.musicsearch.common.map
import com.avp.musicsearch.common.succeeded
import com.avp.musicsearch.dto.FormattedArtist
import com.avp.musicsearch.ui.base.BaseViewModel
import com.avp.musicsearch.usecases.SearchArtistUseCase
import kotlinx.coroutines.CoroutineScope
import org.koin.core.scope.Scope
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
    private val viewModelScope: Scope,
    private val coroutineScope: CoroutineScope,
    private val searchArtistUseCase: SearchArtistUseCase
) : BaseViewModel(viewModelScope, coroutineScope) {

    private val searchResult = MutableLiveData<Either<List<FormattedArtist>>>()

    private val artistListLiveData: LiveData<Event<List<FormattedArtist>?>>

    init {
        artistListLiveData = searchResult.map { either ->
            if ((either.succeeded)) {
                Event((either as Either.Success).data)
            } else {
                Timber.e((either as Either.Error).exception)
                Event(null)
            }
        }

    }

    fun doSearch(query: String): LiveData<Event<List<FormattedArtist>?>> {
        searchArtistUseCase(query, searchResult)
        return artistListLiveData
    }
}