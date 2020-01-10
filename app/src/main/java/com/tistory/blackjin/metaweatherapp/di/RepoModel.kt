package com.tistory.blackjin.metaweatherapp.di

import com.tistory.blackjin.metaweatherapp.data.LocationRepositoryImpl
import com.tistory.blackjin.metaweatherapp.domain.repository.LocationRepository
import org.koin.dsl.module

val repoModule = module {

    single<LocationRepository> {
        LocationRepositoryImpl(get())
    }
}