package com.tistory.blackjin.metaweatherapp.presenter.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.tistory.blackjin.metaweatherapp.base.BaseViewModel
import com.tistory.blackjin.metaweatherapp.domain.usecase.WeatherByLocationUsecase
import com.tistory.blackjin.metaweatherapp.presenter.model.LocalWeatherItem
import timber.log.Timber

class WeatherViewModel(
    private val usecase: WeatherByLocationUsecase
) : BaseViewModel() {

    private val _items = MutableLiveData<List<LocalWeatherItem?>>()
    val items: LiveData<List<LocalWeatherItem?>> get() = _items

    private val _isLoading = MutableLiveData<Boolean>(true)
    val isLoading: LiveData<Boolean> get() = _isLoading

    init {
        usecase.weathersSubject.subscribe({
            if (usecase.isComplete()) {
                hideLoading()
                _items.postValue(it.values.toList())
            }
        }) {
            hideLoading()
            Timber.e("$it")
        }.also {
            compositeDisposable.add(it)
        }
    }

    fun loadWeathers(query: String) {
        usecase.fetch(query)
            .subscribe({
                for (disposable in it) {
                    compositeDisposable.addAll(disposable)
                }
            }) {
                hideLoading()
                Timber.e(it)
            }.also {
                compositeDisposable.add(it)
            }
    }

    private fun hideLoading() {
        _isLoading.postValue(false)
    }
}