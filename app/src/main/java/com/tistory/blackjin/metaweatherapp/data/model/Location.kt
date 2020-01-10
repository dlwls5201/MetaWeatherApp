package com.tistory.blackjin.metaweatherapp.data.model

import com.google.gson.annotations.SerializedName

data class Location(
    @SerializedName("title")
    val title: String = "",
    @SerializedName("location_type")
    val locationType: String = "",
    @SerializedName("woeid")
    val woeid: Int = 0,
    @SerializedName("latt_long")
    val lattLong: String = ""
)