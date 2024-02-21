package com.sadok.kata.domain.use_case

import com.sadok.kata.common.Resource
import com.sadok.kata.domain.model.Weather
import com.sadok.kata.domain.repository.WeatherRepository
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class GetWeatherByListCityNameUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    operator fun invoke(listCityName: List<String>, delayTime: Long): Flow<Resource<List<Weather>>> = flow {
        val weathers = mutableListOf<Weather>()
        emit(Resource.Loading<List<Weather>>(0))

        listCityName.forEachIndexed { index, element ->
            when(val response = repository.getWeatherByCityName(element)) {
                is Resource.Success -> {
                    if(response.data != null) {
                        delay(delayTime)
                        weathers.add(response.data)
                        emit(Resource.Loading<List<Weather>>(index+1))
                    }
                    else {
                        emit(Resource.Error<List<Weather>>("Empty Data"))
                    }
                }
                else -> {
                    emit(Resource.Error<List<Weather>>(response.message ?: "Something went wrong"))
                }
            }

        }
        if (weathers.isNotEmpty()) {
            delay(delayTime)
            emit(Resource.Loading<List<Weather>>(listCityName.size+1))
            emit(Resource.Success<List<Weather>>(weathers.toList()))
        }

    }
}