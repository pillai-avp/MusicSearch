package com.avp.musicsearch.common

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 22 January 2020
 */

val DEEZER_ACCESS_TOKEN: String = "7092e933758f7b14c865f616504fce2a"
val BASE_API = "https://api.deezer.com"


/** Uses `Transformations.map` on a LiveData */
fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}