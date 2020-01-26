package com.avp.musicsearch.ui.album

import com.avp.musicsearch.ui.album_list.AlbumDetailsViewModel
import com.avp.musicsearch.usecases.GetTrackListUsecase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 24 January 2020
 */

private const val scopeName = "AlbumDetailsActivity_scope"

val albumDetailsModule = module {

    factory { TracksAdapter() }

    viewModel {
        val viewModelScope = getKoin().getOrCreateScope(
            "id_$scopeName",
            named<TracksActivity>()
        )
        AlbumDetailsViewModel(viewModelScope, viewModelScope.get(), viewModelScope.get())
    }

    factory {
        val viewModelScope = getKoin().getOrCreateScope(
            "id_$scopeName",
            named<TracksActivity>()
        )
        GetTrackListUsecase(viewModelScope.get(), get())
    }

    scope(named<TracksActivity>()) {
        scoped {
            Job()
        }
        scoped {
            val job: Job = get()
            CoroutineScope(Dispatchers.Main + job)
        }
    }
}