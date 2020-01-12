package com.tistory.blackjin.metaweatherapp.presenter.model

import com.tistory.blackjin.metaweatherapp.R

data class LocalWeatherItem(
    val local: String = "",
    val today: WeatherItem = WeatherItem(),
    val tomorrow: WeatherItem = WeatherItem()
) {
    data class WeatherItem(
        val weatherSummary: String = "",
        val weatherImg: Int = R.drawable.ic_launcher_foreground,
        val weatherTemp: Int = 0,
        val humidity: String = ""
    )
}