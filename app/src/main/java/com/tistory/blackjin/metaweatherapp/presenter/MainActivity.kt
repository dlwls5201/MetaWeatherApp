package com.tistory.blackjin.metaweatherapp.presenter

import android.os.Bundle
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.tistory.blackjin.metaweatherapp.R
import com.tistory.blackjin.metaweatherapp.base.BaseActivity
import com.tistory.blackjin.metaweatherapp.databinding.ActivityMainBinding
import com.tistory.blackjin.metaweatherapp.presenter.adapter.WeatherAdapter
import com.tistory.blackjin.metaweatherapp.presenter.viewmodel.WeatherViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    SwipeRefreshLayout.OnRefreshListener {

    private val weatherViewModel: WeatherViewModel by viewModel()

    private val weatherAdapter = WeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.weatherModel = weatherViewModel

        initObserve()
        setupRecyclerView()
        setupSwipeRefresh()
        loadWeather()
    }

    private fun initObserve() {
        weatherViewModel.items.observe(this, Observer {
            weatherAdapter.replaceAll(it)
            if (binding.srlMainWeather.isRefreshing) {
                hideSwipeRefreshLoading()
            }
        })
    }

    private fun setupRecyclerView() {
        with(binding.rvMainWeather) {
            adapter = weatherAdapter

            addItemDecoration(DividerItemDecoration(this.context, LinearLayout.VERTICAL).apply {
                ContextCompat.getDrawable(context, R.drawable.line_weather_divider)?.let {
                    setDrawable(it)
                }
            })
        }
    }

    private fun setupSwipeRefresh() {
        binding.srlMainWeather.setOnRefreshListener(this)
    }

    override fun onRefresh() {
        weatherAdapter.clear()
        weatherViewModel.loadWeathers("se")
    }

    private fun hideSwipeRefreshLoading() {
        binding.srlMainWeather.isRefreshing = false
    }

    private fun loadWeather() {
        if (weatherViewModel.items.value == null) {
            weatherViewModel.loadWeathers("se")
        }
    }
}
