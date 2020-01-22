package com.avp.musicsearch.di

import com.avp.musicsearch.repo.AlbumRepository
import com.avp.musicsearch.repo.AlbumRepositoryImpl
import com.avp.musicsearch.ui.search.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 21 January 2020
 */

val searchModule = module {
    factory<AlbumRepository> { AlbumRepositoryImpl() }
    viewModel { SearchViewModel(get()) }
}
