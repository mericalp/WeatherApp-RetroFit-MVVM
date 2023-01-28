package com.example.weatherapp.repository

import Root
import com.example.weatherapp.api.RetrofitInstance
import retrofit2.Response

class Repository {

    suspend fun getWeatherData(): Response<Root>{
        return RetrofitInstance.api.getWeatherData()
    }

    suspend fun getWeatherWeekData(): Root {
        return  RetrofitInstance.api.getWeatherWeekData()
    }

}