package com.avp.musicsearch.ui.album_list

import com.avp.musicsearch.usecases.GetAlbumsListUsecase
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

private const val scopeName = "AlbumListActivity_scope"

val albumListModule = module {

    factory { AlbumsAdapter() }


    viewModel {
        val viewModelScope = getKoin().getOrCreateScope(
            "id_${scopeName}",
            named<AlbumListActivity>()
        )
        AlbumListViewModel(viewModelScope, viewModelScope.get(), viewModelScope.get())
    }

    factory {
        val viewModelScope = getKoin().getOrCreateScope(
            "id_${scopeName}",
            named<AlbumListActivity>()
        )
        GetAlbumsListUsecase(viewModelScope.get(), get())
    }

    scope(named<AlbumListActivity>()) {
        scoped {
            Job()
        }
        scoped {
            val job: Job = get()
            CoroutineScope(Dispatchers.Main + job)
        }
    }
}