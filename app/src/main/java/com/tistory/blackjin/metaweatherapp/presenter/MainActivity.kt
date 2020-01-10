package com.tistory.blackjin.metaweatherapp.presenter

import android.os.Bundle
import android.view.View
import androidx.lifecycle.Observer
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tistory.blackjin.metaweatherapp.R
import com.tistory.blackjin.metaweatherapp.base.BaseActivity
import com.tistory.blackjin.metaweatherapp.databinding.ActivityMainBinding
import com.tistory.blackjin.metaweatherapp.presenter.adapter.WeatherAdapter
import com.tistory.blackjin.metaweatherapp.presenter.viewmodel.WeatherViewModel
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    SwipeRefreshLayout.OnRefreshListener {

    private val weatherViewModel: WeatherViewModel by viewModel()

    private val weatherAdapter = WeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //아이템이 이미 있는 경우 받아오지 않습니다.
        weatherViewModel.loadWeathers("se")

        weatherViewModel.items.observe(this, Observer {
            Timber.d("items : $it")
            pbMain.visibility = View.GONE
            weatherAdapter.replaceAll(it)
        })

        with(rvMainWeather) {
            adapter = weatherAdapter
        }

        srlMainWeather.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        srlMainWeather.isRefreshing = false
    }
}
