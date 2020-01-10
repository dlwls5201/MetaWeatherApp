package com.tistory.blackjin.metaweatherapp.di

import com.tistory.blackjin.metaweatherapp.BuildConfig
import com.tistory.blackjin.metaweatherapp.data.provider.AppSchedulerProvider
import com.tistory.blackjin.metaweatherapp.domain.provider.SchedulersProvider
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {

    single(named("debug")) { BuildConfig.DEBUG }

    single<SchedulersProvider> { AppSchedulerProvider() }
}