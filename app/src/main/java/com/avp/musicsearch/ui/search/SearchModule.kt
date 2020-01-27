package com.avp.musicsearch.ui.search

import com.avp.musicsearch.di.getScope
import com.avp.musicsearch.di.provideCoroutineScope
import com.avp.musicsearch.di.provideJob
import com.avp.musicsearch.usecases.SearchArtistUseCase
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: pillai.avp@gmail.com
 *
 * Date: 24 January 2020
 */


val searchModule = module {

    factory { ArtistsAdapter() }

    scope(named<SearchActivity>()) {
        scoped {
            provideJob()
        }
        scoped {
            provideCoroutineScope(get())
        }
    }

    factory {
        val koinScope = named<SearchActivity>().getScope(this)
        SearchArtistUseCase(koinScope.get(), get())
    }

    viewModel {
        val koinScope = named<SearchActivity>().getScope(this)
        SearchViewModel(koinScope, koinScope.get(), get())
    }
}