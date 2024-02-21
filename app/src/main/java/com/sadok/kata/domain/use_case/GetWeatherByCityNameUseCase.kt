package com.sadok.kata.domain.use_case

import com.sadok.kata.common.Resource
import com.sadok.kata.domain.model.Weather
import com.sadok.kata.domain.repository.WeatherRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherByCityNameUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(cityName: String): Flow<Resource<Weather>> = flow {
        when (val response = repository.getWeatherByCityName(cityName)) {
            is Resource.Success -> {
                if (response.data != null) {
                    emit(Resource.Success<Weather>(response.data))
                } else {
                    emit(Resource.Error<Weather>("Empty Data"))
                }
            }
            else -> {
                emit(Resource.Error<Weather>(response.message ?: "Something went wrong"))
            }
        }
    }
}