package com.tistory.blackjin.metaweatherapp

import android.app.Application
import com.tistory.blackjin.metaweatherapp.di.*
import org.koin.android.ext.koin.androidContext
import org.koin.android.logger.AndroidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.EmptyLogger
import timber.log.Timber

class MetaWeatherApplicaion : Application() {

    override fun onCreate() {
        super.onCreate()

        setupTimber()
        setupKoin()
    }

    private fun setupTimber() {
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }

    private fun setupKoin() {
        startKoin {
            logger(
                if (BuildConfig.DEBUG) {
                    AndroidLogger()
                } else {
                    EmptyLogger()
                }
            )

            androidContext(this@MetaWeatherApplicaion)

            modules(
                listOf(
                    appModule,
                    networkModule,
                    repoModule,
                    usecaseModule,
                    viewModelModule
                )
            )
        }
    }
}