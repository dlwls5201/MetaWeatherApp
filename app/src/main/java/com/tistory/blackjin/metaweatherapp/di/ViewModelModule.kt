package com.tistory.blackjin.metaweatherapp.di

import com.tistory.blackjin.metaweatherapp.presenter.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        WeatherViewModel(
            get()
        )
    }
}