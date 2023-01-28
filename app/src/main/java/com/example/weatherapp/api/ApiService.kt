package com.example.weatherapp.api

import Root
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("forecast?")
    suspend fun getWeatherData(
        @Query("q") q: String = city,
        @Query("appid") appid: String = API_KEY,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "pt_br",
    ): Response<Root>

    @GET("forecast?")
    suspend fun getWeatherWeekData(
        @Query("q") q: String = city,
        @Query("appid") appid: String = API_KEY,
        @Query("units") units: String = "metric",
        @Query("lang") lang: String = "pt_br",
    ): Root

    companion object {
        val API_KEY = "8df4fdcc8e95f75336e5a11d00e43623"
        var city = "São Paulo"
    }
}
