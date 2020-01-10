package com.tistory.blackjin.metaweatherapp.domain.usecase

import com.tistory.blackjin.metaweatherapp.data.model.Location
import com.tistory.blackjin.metaweatherapp.data.model.mapToPresentation
import com.tistory.blackjin.metaweatherapp.domain.provider.SchedulersProvider
import com.tistory.blackjin.metaweatherapp.domain.repository.LocationRepository
import com.tistory.blackjin.metaweatherapp.presenter.model.LocalWeatherItem
import io.reactivex.Single
import io.reactivex.disposables.Disposable
import io.reactivex.subjects.ReplaySubject

class WeatherByLocationUsecase(
    private val locationRepository: LocationRepository,
    private val schedulersProvider: SchedulersProvider
) {

    val weathersSubject =
        ReplaySubject.create<LinkedHashMap<Int, LocalWeatherItem?>>()

    private val locationWeatherItems = LinkedHashMap<Int, LocalWeatherItem?>()

    //locationWeatherHash에 null이 없어야 모든 작업이 완료됩니다.
    fun isComplete() = !locationWeatherItems.containsValue(null)

    fun fetch(query: String) = locationRepository.search(query)
        .flatMap { locations ->

            val disposables = Array<Disposable?>(locations.size, init = { null })

            for ((index, location) in locations.withIndex()) {

                //비동기로 작업이 이뤄지므로 데이터의 순서를 보장하기 위해 LinkedHashMap을 사용합니다.
                //초기값은 null로 설정합니다.
                locationWeatherItems[location.woeid] = null
                disposables[index] = get(location)
            }

            Single.just(disposables)
        }
        .subscribeOn(schedulersProvider.io())

    //
    private fun get(location: Location): Disposable {
        return locationRepository.getInfo(location.woeid)
            .map {
                if (it.consolidatedWeather.size == 1) {
                    locationWeatherItems[location.woeid] = LocalWeatherItem(
                        local = location.title,
                        today = it.consolidatedWeather[0].mapToPresentation()
                    )
                } else if (it.consolidatedWeather.size > 1) {
                    locationWeatherItems[location.woeid] = LocalWeatherItem(
                        local = location.title,
                        today = it.consolidatedWeather[0].mapToPresentation(),
                        tomorrow = it.consolidatedWeather[1].mapToPresentation()
                    )
                }
            }
            .subscribeOn(schedulersProvider.io())
            .subscribe({
                weathersSubject.onNext(locationWeatherItems)
            }) {
                locationWeatherItems[location.woeid] = LocalWeatherItem()
            }
    }
}