package com.avp.musicsearch.common

import android.widget.ImageView
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.avp.musicsearch.R
import com.bumptech.glide.Glide
import de.hdodenhof.circleimageview.CircleImageView
import okhttp3.Interceptor
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: pillai.avp@gmail.com
 *
 * Date: 22 January 2020
 */

val DEEZER_ACCESS_TOKEN: String = "7092e933758f7b14c865f616504fce2a"
val BASE_API = "https://api.deezer.com"


/** Uses `Transformations.map` on a LiveData */
fun <X, Y> LiveData<X>.map(body: (X) -> Y): LiveData<Y> {
    return Transformations.map(this, body)
}


fun createLoggingInterceptor(): Interceptor {
    val logger = HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }
    logger.level = HttpLoggingInterceptor.Level.BODY
    return logger
}

fun createHeaderLoggingInterceptor(): Interceptor {
    val logger = HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }
    logger.level = HttpLoggingInterceptor.Level.HEADERS
    return logger
}


fun CircleImageView.setImageUrl(
    img: ImageView,
    url: String?
) {
    url?.let {
        Glide.with(img.context)
            .load(url)
            .placeholder(R.mipmap.ic_launcher)
            .into(img)
    }
}