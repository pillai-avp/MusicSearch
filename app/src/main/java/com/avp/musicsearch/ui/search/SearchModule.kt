package com.avp.musicsearch.ui.search

import com.avp.musicsearch.usecases.SearchArtistUseCase
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

private const val scopeName = "SearchActivity_scope"

val searchModule = module {

    factory { ArtistsAdapter() }

    scope(named<SearchActivity>()) {
        scoped {
            Job()
        }
        scoped {
            val job: Job = get()
            CoroutineScope(Dispatchers.Main + job)
        }
    }

    factory {
        val viewModelScope = getKoin().getOrCreateScope("id_$scopeName", named<SearchActivity>())
        SearchArtistUseCase(viewModelScope.get(), get())
    }

    viewModel {
        val viewModelScope = getKoin().getOrCreateScope("id_$scopeName", named<SearchActivity>())
        SearchViewModel(viewModelScope, viewModelScope.get(), get())
    }
}