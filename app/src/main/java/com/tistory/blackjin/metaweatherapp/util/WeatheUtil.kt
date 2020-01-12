package com.tistory.blackjin.metaweatherapp.util

import com.tistory.blackjin.metaweatherapp.R

object WeatheUtil {

    fun getImage(weatherStateAbbr: String) = when (weatherStateAbbr) {
        "sn" -> R.drawable.ic_weather_snow
        "sl" -> R.drawable.ic_weather_sleet
        "h" -> R.drawable.ic_weather_hail
        "t" -> R.drawable.ic_weather_thunderstorm
        "hr" -> R.drawable.ic_weather_heavy_rain
        "lr" -> R.drawable.ic_weather_light_rain
        "s" -> R.drawable.ic_weather_showers
        "hc" -> R.drawable.ic_weather_heavy_cloud
        "lc" -> R.drawable.ic_weather_light_cloud
        "c" -> R.drawable.ic_weather_clear
        else -> R.drawable.ic_launcher_foreground
    }
}