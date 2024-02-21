package com.sadok.kata.data.remote.dto

import com.sadok.kata.domain.model.Weather
import com.sadok.kata.utils.Constants

data class ResponseDto(
    val base: String,
    val clouds: CloudsDto,
    val cod: Int,
    val coord: CoordinateDto,
    val dt: Int,
    val id: Int,
    val main: MainDto,
    val name: String,
    val sys: SysDto,
    val timezone: Int,
    val visibility: Int,
    val weather: List<WeatherDto>,
    val wind: WindDto
)
fun ResponseDto.toWeather() : Weather{
    return Weather(
        id = weather[0].id,
        cityName = name,
        temperature = main.temp.toInt(),
        description = weather[0].description,
        icon = Constants.ICON_URL+weather[0].icon+".png",

    )
}