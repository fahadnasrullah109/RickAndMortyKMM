package com.rickandmortykmm.android

import android.app.Application
import com.rickandmortykmm.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

class MyApp : Application(){

    override fun onCreate() {
        super.onCreate()
        initKoin(baseUrl = "https://rickandmortyapi.com/api/", enableNetworkLogs = BuildConfig.DEBUG) {
            androidContext(this@MyApp)
            modules(
                listOf(module {
                    /**
                     * android specific modules
                     */
                })
            )
        }
    }
}