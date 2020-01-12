package com.tistory.blackjin.metaweatherapp.extension

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import com.tistory.blackjin.metaweatherapp.R

@BindingAdapter("bind:setWeatherTemp")
fun TextView.setWeatherTemp(temp: Int) {
    val colorId = if (temp >= 0) R.color.red else R.color.blue
    setTextColor(ContextCompat.getColor(context, colorId))

    val strTemp = temp.toString()
    val tempLength = strTemp.length
    val strWeatherTemp = "$strTemp°C"
    val ssb = SpannableStringBuilder(strWeatherTemp)

    ssb.setSpan(
        StyleSpan(Typeface.BOLD),
        0,
        tempLength,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    text = ssb

}

@BindingAdapter("bind:setWeatherHumidity")
fun TextView.setWeatherHumidity(humidity: String) {

    val humidityLength = humidity.length
    val strWeatherTemp = "$humidity%"
    val ssb = SpannableStringBuilder(strWeatherTemp)

    ssb.setSpan(
        StyleSpan(Typeface.BOLD),
        0,
        humidityLength,
        Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
    )

    text = ssb
}