package com.avp.musicsearch.di

import com.avp.musicsearch.BuildConfig
import com.avp.musicsearch.common.BASE_API
import com.avp.musicsearch.net.DeezerAPI
import com.avp.musicsearch.repo.AlbumRepository
import com.avp.musicsearch.repo.AlbumRepositoryImpl
import com.avp.musicsearch.ui.search.SearchViewModel
import com.avp.musicsearch.usecases.SearchArtistUseCase
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import timber.log.Timber
import java.util.concurrent.TimeUnit


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: arun.vijayan.pillai@shortcut.no
 *
 * Date: 21 January 2020
 */

val appModule = module {

    factory { Job() }
    factory {
        val job: Job = get()
        CoroutineScope(Dispatchers.Main + job)
    }

}


val apiModules = module {
    single { GsonBuilder().create() }

    single {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(createLoggingInterceptor())
            builder.addInterceptor(createHeaderLoggingInterceptor())
        }
        //builder.addInterceptor(createHeaderInterceptor())
        builder.connectTimeout(60, TimeUnit.SECONDS)
        builder.readTimeout(60, TimeUnit.SECONDS)
        builder.build()

    }

    single<Retrofit> {
        Retrofit.Builder()
            .baseUrl(BASE_API)
            .addConverterFactory(GsonConverterFactory.create(get()))
            .client(get())
            .build()
    }

    single<DeezerAPI> {
        val retrofit: Retrofit = get()
        retrofit.create<DeezerAPI>(DeezerAPI::class.java)
    }
}


private fun createLoggingInterceptor(): Interceptor {
    val logger = HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }
    logger.level = HttpLoggingInterceptor.Level.BODY
    return logger
}

private fun createHeaderLoggingInterceptor(): Interceptor {
    val logger = HttpLoggingInterceptor { message -> Timber.tag("OkHttp").d(message) }
    logger.level = HttpLoggingInterceptor.Level.HEADERS
    return logger
}


val searchModule = module {
    factory<AlbumRepository> { AlbumRepositoryImpl(get()) }
    factory { SearchArtistUseCase(get(), get()) }
    viewModel { SearchViewModel(get(), get()) }
}