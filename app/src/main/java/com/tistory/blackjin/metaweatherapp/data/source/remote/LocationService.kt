package com.tistory.blackjin.metaweatherapp.data.source.remote

import com.tistory.blackjin.metaweatherapp.data.model.Location
import com.tistory.blackjin.metaweatherapp.data.model.LocationInfo
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface LocationService {

    @GET("location/search/?")
    fun searchLocation(
        @Query("query") query: String
    ): Single<List<Location>>

    @GET("location/{woeid}")
    fun getLocationInfo(
        @Path("woeid") woeid: Int
    ): Single<LocationInfo>
}