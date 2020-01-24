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

val searchModule = module {
    scope(named<SearchActivity>()) {
        scoped { ArtistsAdapter() }
    }

    scope(named("search_scope")) {
        scoped {
            Job()
        }
        scoped {
            val job: Job = get()
            CoroutineScope(Dispatchers.Main + job)
        }
    }

    factory {
        val viewModelScope = getKoin().getOrCreateScope("search_scope_id", named("search_scope"))
        SearchArtistUseCase(viewModelScope.get(), get())
    }

    viewModel {
        val viewModelScope = getKoin().getOrCreateScope("search_scope_id", named("search_scope"))
        SearchViewModel(viewModelScope, viewModelScope.get(), get())
    }
}