package com.sadok.kata.domain.repository

import com.sadok.kata.common.Resource
import com.sadok.kata.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeatherByCityName(city: String) : Resource<Weather>
}