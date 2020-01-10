package com.tistory.blackjin.metaweatherapp.data.model

import com.google.gson.annotations.SerializedName
import com.tistory.blackjin.metaweatherapp.presenter.model.LocalWeatherItem

data class LocationInfo(
    @SerializedName("consolidated_weather")
    val consolidatedWeather: List<ConsolidatedWeather>
) {
    /**
     * @param weatherStateName 날씨 요약
     * @param weatherStateAbbr 아이콘 이미지
     * @param theTemp 현재 날씨
     * @param humidity 습도
     */
    data class ConsolidatedWeather(
        @SerializedName("id")
        val id: Long,
        @SerializedName("weather_state_name")
        val weatherStateName: String,
        @SerializedName("weather_state_abbr")
        val weatherStateAbbr: String,
        @SerializedName("the_temp")
        val theTemp: Double,
        @SerializedName("humidity")
        val humidity: Int
    )
}

fun LocationInfo.ConsolidatedWeather.mapToPresentation() =
    LocalWeatherItem.WeatherItem(
        weatherSummary = weatherStateName,
        weatherImg = weatherStateAbbr,
        weatherNow = theTemp.toString(),
        humidity = humidity.toString()
    )