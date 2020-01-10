package com.tistory.blackjin.metaweatherapp.di

import com.tistory.blackjin.metaweatherapp.domain.usecase.WeatherByLocationUsecase
import org.koin.dsl.module

val usecaseModule = module {

    factory {
        WeatherByLocationUsecase(get(), get())
    }
}