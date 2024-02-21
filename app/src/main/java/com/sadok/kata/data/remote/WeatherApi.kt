package com.sadok.kata.data.remote

import com.sadok.kata.BuildConfig
import com.sadok.kata.data.remote.dto.ResponseDto
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {
    @GET("/data/2.5/weather")
    suspend fun getWeatherDataByCityName(
        @Query("q") cityName: String,
        @Query("units") unit: String = "metric",
        @Query("APPID") apiKey: String = BuildConfig.API_KEY,
        @Query("lang") language: String = "Fr"
    ): ResponseDto
}