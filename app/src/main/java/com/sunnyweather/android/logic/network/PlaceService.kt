package com.sunnyweather.android.logic.network

import com.sunnyweather.android.SunnyWeatherApplication
import com.sunnyweather.android.logic.model.PlaceResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface PlaceService {
    @GET("v3/geocode/geo?output=JSON&key=${SunnyWeatherApplication.MapToken}")
    fun searchPlaces(
        @Query("address") query: String,
        @Query("key") key: String
    ): Call<PlaceResponse>
}