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
    /*scope(named<AlbumListActivity>()) {
        scoped { AlbumsAdapter() }
    }*/

    factory { AlbumsAdapter() }


    viewModel {
        val viewModelScope = getKoin().getOrCreateScope(
            "id_${scopeName}",
            named(scopeName)
        )
        AlbumListViewModel(viewModelScope, viewModelScope.get(), viewModelScope.get())
    }

    factory {
        val viewModelScope = getKoin().getOrCreateScope(
            "id_${scopeName}",
            named(scopeName)
        )
        GetAlbumsListUsecase(viewModelScope.get(), get())
    }

    scope(named(scopeName)) {
        scoped {
            Job()
        }
        scoped {
            val job: Job = get()
            CoroutineScope(Dispatchers.Main + job)
        }
    }
}