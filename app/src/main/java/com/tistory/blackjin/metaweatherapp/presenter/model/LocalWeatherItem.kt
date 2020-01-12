package com.tistory.blackjin.metaweatherapp.presenter.model

data class LocalWeatherItem(
    val local: String = "",
    val today: WeatherItem = WeatherItem(),
    val tomorrow: WeatherItem = WeatherItem()
) {
    data class WeatherItem(
        val weatherSummary: String = "",
        val weatherImg: String = "",
        val weatherTemp: String = "",
        val humidity: String = ""
    )
}