package com.example.crypto.app

import android.app.Application
import com.example.crypto.app.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {
    companion object {
        lateinit var instance: App
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                viewModelModule,
                netModule,
                netApiModule,
                repositoryMappersModule,
                repositoryMappersModule1
            )
        }
        instance = this
    }
}