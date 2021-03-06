package com.avp.musicsearch.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.cancel
import org.koin.core.scope.Scope


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: pillai.avp@gmail.com
 *
 * Date: 22 January 2020
 */
open class BaseViewModel(private val koinScope: Scope, private val coroutineScope: CoroutineScope) :
    ViewModel() {

    @ExperimentalCoroutinesApi
    override fun onCleared() {
        super.onCleared()
        coroutineScope.cancel()
        koinScope.close()
    }
}