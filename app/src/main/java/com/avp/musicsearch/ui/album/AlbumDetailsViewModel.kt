package com.avp.musicsearch.ui.album_list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.avp.musicsearch.common.Either
import com.avp.musicsearch.common.Event
import com.avp.musicsearch.common.map
import com.avp.musicsearch.common.succeeded
import com.avp.musicsearch.dto.Album
import com.avp.musicsearch.ui.base.BaseViewModel
import com.avp.musicsearch.usecases.GetAlbumsListUsecase
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
    private val getAlbumsListUsecase: GetAlbumsListUsecase
) : BaseViewModel(scope, coroutineScope) {

    private val getAlbumsResult = MutableLiveData<Either<List<Album>>>()
    private val albumsLiveData: LiveData<Event<List<Album>?>>

    init {

        albumsLiveData = getAlbumsResult.map { either ->
            if ((either.succeeded)) {
                Event((either as Either.Success).data)
            } else {
                Timber.e((either as Either.Error).exception)
                Event(null)
            }
        }

    }

    fun getAlbumList(name: String): LiveData<Event<List<Album>?>> {
        getAlbumsListUsecase(name, getAlbumsResult)
        return albumsLiveData
    }

}