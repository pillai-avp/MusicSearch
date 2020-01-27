package com.avp.musicsearch.di

import com.avp.musicsearch.BuildConfig
import com.avp.musicsearch.common.BASE_API
import com.avp.musicsearch.common.createHeaderLoggingInterceptor
import com.avp.musicsearch.common.createLoggingInterceptor
import com.avp.musicsearch.net.DeezerAPI
import com.avp.musicsearch.repo.AlbumRepository
import com.avp.musicsearch.repo.AlbumRepositoryImpl
import com.google.gson.GsonBuilder
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import okhttp3.OkHttpClient
import org.koin.core.qualifier.TypeQualifier
import org.koin.core.scope.Scope
import org.koin.dsl.module
import org.koin.ext.getFullName
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


/**
 * Koin configurations for the dependency injection
 *
 * Created by:  Arun Pillai
 * Email: pillai.avp@gmail.com
 *
 * Date: 21 January 2020
 */

/**
 * Every thing for API request are initialized here
 */
val apiModules = module {

    single { GsonBuilder().create() }

    single {
        val builder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            builder.addInterceptor(createLoggingInterceptor())
            builder.addInterceptor(createHeaderLoggingInterceptor())
        }
        //builder.addInterceptor(createHeaderInterceptor())
        builder.connectTimeout(15, TimeUnit.SECONDS)
        builder.readTimeout(15, TimeUnit.SECONDS)
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

/**
 * All the use cases and repository initialization happens here.
 */
val repoModules = module {
    factory<AlbumRepository> { AlbumRepositoryImpl(get()) }
}

fun provideCoroutineScope(job: Job): CoroutineScope {
    return CoroutineScope(Dispatchers.Main + job)
}

fun provideJob(): Job = Job()

fun TypeQualifier.getScope(scope: Scope): Scope {
    return scope.getKoin().getOrCreateScope(this.type.getFullName(), this)
}