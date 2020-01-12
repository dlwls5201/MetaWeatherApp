package com.tistory.blackjin.metaweatherapp.extension

import android.widget.ImageView
import androidx.databinding.BindingAdapter

@BindingAdapter("android:src")
fun ImageView.setImageViewResource(resource: Int) {
    setImageResource(resource)
}
