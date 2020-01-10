package com.tistory.blackjin.metaweatherapp.domain.repository

import com.tistory.blackjin.metaweatherapp.data.model.Location
import com.tistory.blackjin.metaweatherapp.data.model.LocationInfo
import io.reactivex.Single

interface LocationRepository {

    fun search(query: String): Single<List<Location>>

    fun getInfo(woeid: Int): Single<LocationInfo>
}