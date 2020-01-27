package com.avp.musicsearch.ui.album

import com.avp.musicsearch.di.getScope
import com.avp.musicsearch.di.provideCoroutineScope
import com.avp.musicsearch.di.provideJob
import com.avp.musicsearch.ui.album_list.TracksViewModel
import com.avp.musicsearch.usecases.GetTrackListUsecase
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


val tracksModule = module {

    factory { TracksAdapter() }

    viewModel {
        val scopedKoin = named<TracksActivity>().getScope(this)
        TracksViewModel(scopedKoin, scopedKoin.get(), scopedKoin.get())
    }

    factory {
        val scopedKoin = named<TracksActivity>().getScope(this)
        GetTrackListUsecase(scopedKoin.get(), get())
    }

    scope(named<TracksActivity>()) {
        scoped {
            provideJob()
        }
        scoped {
            provideCoroutineScope(get())
        }
    }
}