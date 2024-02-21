package com.sadok.kata.presentation.weather

import com.sadok.kata.domain.model.Weather

data class WeatherState(
    val isLoading: Boolean = true,
    val weathers : List<Weather> = emptyList(),
    val error: String = ""
)
