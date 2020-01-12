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
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main),
    SwipeRefreshLayout.OnRefreshListener {

    private val weatherViewModel: WeatherViewModel by viewModel()

    private val weatherAdapter = WeatherAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Timber.d("weatherViewModel items : ${weatherViewModel.items.value}")
        if (weatherViewModel.items.value == null) {
            weatherViewModel.loadWeathers("se")
        }

        weatherViewModel.items.observe(this, Observer {
            Timber.d("items : $it")
            weatherAdapter.replaceAll(it)
            hideSwipeRefreshLoading()
        })

        setupRecyclerView()
        setupSwipeRefresh()

    }

    override fun onRefresh() {
        weatherViewModel.loadWeathers("se")
    }

    private fun setupRecyclerView() {
        with(rvMainWeather) {
            adapter = weatherAdapter

            val context = this.context
            addItemDecoration(DividerItemDecoration(context, LinearLayout.VERTICAL).apply {
                setDrawable(ContextCompat.getDrawable(context, R.drawable.line_weather_divider)!!)
            })
        }
    }

    private fun setupSwipeRefresh() {
        srlMainWeather.setOnRefreshListener(this)
    }

    private fun hideSwipeRefreshLoading() {
        srlMainWeather.isRefreshing = false
    }
}
