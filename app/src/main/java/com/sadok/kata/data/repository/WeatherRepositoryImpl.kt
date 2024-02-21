package com.sadok.kata.data.repository

import com.sadok.kata.common.Resource
import com.sadok.kata.data.remote.WeatherApi
import com.sadok.kata.data.remote.dto.toWeather
import com.sadok.kata.domain.repository.WeatherRepository
import com.sadok.kata.domain.model.Weather
import com.sadok.kata.utils.Constants
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(private val api: WeatherApi): WeatherRepository {
    override suspend fun getWeatherByCityName(city: String): Resource<Weather> {
        return try {
            Resource.Success(
                api.getWeatherDataByCityName(city).toWeather()
            )
        } catch (e: Exception) {
            Resource.Error(e.message ?: Constants.UNKNOWN_ERROR)
        }
    }

}