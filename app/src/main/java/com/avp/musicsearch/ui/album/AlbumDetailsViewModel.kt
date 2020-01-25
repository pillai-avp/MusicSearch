package com.avp.musicsearch.ui.album_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.avp.musicsearch.common.Either
import com.avp.musicsearch.common.Event
import com.avp.musicsearch.common.map
import com.avp.musicsearch.common.succeeded
import com.avp.musicsearch.dto.TrackData
import com.avp.musicsearch.ui.base.BaseViewModel
import com.avp.musicsearch.usecases.GetTrackListUsecase
import kotlinx.coroutines.CoroutineScope
import org.koin.core.scope.Scope
import timber.log.Timber


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 23 January 2020
 */
class AlbumDetailsViewModel(
    private val scope: Scope,
    private val coroutineScope: CoroutineScope,
    private val getTrackListUsecase: GetTrackListUsecase
) : BaseViewModel(scope, coroutineScope) {

    private val getTracksResult = MutableLiveData<Either<List<TrackData>>>()
    private val tracksLiveData: LiveData<Event<List<TrackData>?>>

    init {

        tracksLiveData = getTracksResult.map { either ->
            if ((either.succeeded)) {
                Event((either as Either.Success).data)
            } else {
                Timber.e((either as Either.Error).exception)
                Event(null)
            }
        }

    }

    fun getTrackList(url: String): LiveData<Event<List<TrackData>?>> {
        getTrackListUsecase(url, getTracksResult)
        return tracksLiveData
    }

}