package com.sadok.kata.domain.model


data class Weather(
    val id: Int,
    val cityName: String,
    val temperature: Int,
    val description: String,
    val icon: String
)
