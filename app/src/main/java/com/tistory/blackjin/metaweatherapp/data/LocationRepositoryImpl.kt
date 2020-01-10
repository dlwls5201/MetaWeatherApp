package com.tistory.blackjin.metaweatherapp.data

import com.tistory.blackjin.metaweatherapp.data.model.Location
import com.tistory.blackjin.metaweatherapp.data.model.LocationInfo
import com.tistory.blackjin.metaweatherapp.data.source.remote.LocationService
import com.tistory.blackjin.metaweatherapp.domain.repository.LocationRepository
import io.reactivex.Single

class LocationRepositoryImpl(
    private val locationService: LocationService
) : LocationRepository {

    override fun search(query: String): Single<List<Location>> {
        return locationService.searchLocation(query)
    }

    override fun getInfo(woeid: Int): Single<LocationInfo> {
        return locationService.getLocationInfo(woeid)
    }
}