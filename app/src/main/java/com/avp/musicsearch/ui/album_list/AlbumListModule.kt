package com.avp.musicsearch.ui.album_list

import com.avp.musicsearch.di.getScope
import com.avp.musicsearch.di.provideCoroutineScope
import com.avp.musicsearch.di.provideJob
import com.avp.musicsearch.usecases.GetAlbumsListUsecase
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


val albumListModule = module {

    factory { AlbumsAdapter() }


    viewModel {
        val viewModelScope = named<AlbumListActivity>().getScope(this)
        AlbumListViewModel(viewModelScope, viewModelScope.get(), viewModelScope.get())
    }

    factory {
        val viewModelScope = named<AlbumListActivity>().getScope(this)
        GetAlbumsListUsecase(viewModelScope.get(), get())
    }

    scope(named<AlbumListActivity>()) {
        scoped {
            provideJob()
        }
        scoped {
            provideCoroutineScope(get())
        }
    }
}