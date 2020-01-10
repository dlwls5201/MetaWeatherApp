package com.tistory.blackjin.metaweatherapp.presenter

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.tistory.blackjin.metaweatherapp.R
import com.tistory.blackjin.metaweatherapp.presenter.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : AppCompatActivity() {

    private val weatherViewModel: WeatherViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //아이템이 이미 있는 경우 받아오지 않습니다.
        weatherViewModel.loadWeathers("se")

        weatherViewModel.items.observe(this, Observer {
            Timber.d("items : $it")
        })
    }
}
