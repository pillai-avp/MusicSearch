package com.avp.musicsearch

import android.annotation.SuppressLint
import android.app.Application
import android.util.Log
import com.avp.musicsearch.di.apiModules
import com.avp.musicsearch.di.repoModules
import com.avp.musicsearch.ui.album.tracksModule
import com.avp.musicsearch.ui.album_list.albumListModule
import com.avp.musicsearch.ui.search.searchModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber


/**
 *
 *
 * Created by:  Arun Pillai
 * Email: pillai.avp@gmail.com
 *
 * Date: 21 January 2020
 */
class MusicApplication : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin()
        initTimber()
    }

    private fun initTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(CrashReportingTree())
        }
    }

    private fun initKoin() {
        startKoin {
            androidLogger()
            androidContext(this@MusicApplication)
            modules(mutableListOf(apiModules,
                repoModules,
                searchModule,
                albumListModule,
                tracksModule
            )
            )
        }
    }
}

class CrashReportingTree : Timber.Tree() {

    @SuppressLint("LogNotTimber")
    override fun log(priority: Int, tag: String?, message: String, t: Throwable?) {
        if (priority == Log.VERBOSE || priority == Log.DEBUG) {
            return
        }

        if (t != null) {
            if (priority == Log.ERROR) {
                Log.e(tag, message)
            } else if (priority == Log.WARN) {
                Log.w(tag, message)
            }
        }
    }

}