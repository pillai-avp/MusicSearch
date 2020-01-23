package com.avp.musicsearch.ui.abstract

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.cancel
import org.koin.core.scope.Scope


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 22 January 2020
 */
open class BaseViewModel(private val viewModelScope: Scope, private val scope: CoroutineScope) :
    ViewModel() {

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
        viewModelScope.close()
    }
}