package com.example.weatherapp.Model

data class Weather(
    val id: Long,
    val main: String,
    val description: String,
    val icon: String,
)