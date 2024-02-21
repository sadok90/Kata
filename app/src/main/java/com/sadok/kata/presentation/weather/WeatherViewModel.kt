package com.sadok.kata.presentation.weather

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.sadok.kata.common.Resource
import com.sadok.kata.domain.use_case.GetWeatherByListCityNameUseCase
import com.sadok.kata.domain.use_case.RefreshLoadingMessageUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WeatherViewModel @Inject constructor(
    private val getWeatherByListCityNameUseCase: GetWeatherByListCityNameUseCase,
    private val getMessageUseCase: RefreshLoadingMessageUseCase
) : ViewModel() {
    private var getListWeatherJob: Job? = null
    private var getMessageJob: Job? = null

    private val _weatherState = mutableStateOf(WeatherState())
    val weatherState: State<WeatherState> = _weatherState


    private val _loadingMessageState = mutableStateOf(String())
    val loadingMessageState: State<String> = _loadingMessageState


    private val _loadingValueState = mutableFloatStateOf(0f)
    val loadingValueState: State<Float> = _loadingValueState


    private val loadingMessages = listOf(
        "Nous téléchargeons les données...",
        "C'est presque fini...",
        "Plus que quelques secondes avant d'avoir le résultat..."
    )
    private val cities = listOf("Rennes", "Paris", "Nantes", "Bordeaux", "Lyon")

    private fun getWeatherByListCityName(cities: List<String>) = viewModelScope.launch(Dispatchers.IO) {
        val delayTime = 10000L
        val nbRequest = 6
        _weatherState.value = WeatherState(true)

        getListWeatherJob?.cancel()
        getListWeatherJob = getWeatherByListCityNameUseCase(cities, delayTime)
            .onEach { result ->
                println("getWeatherByListCityName "+result.toString())

                when (result) {
                    is Resource.Success -> {
                        _weatherState.value = WeatherState(false)
                        if (result.data != null) {
                            _weatherState.value = WeatherState(weathers = result.data)
                        } else {
                            _weatherState.value = WeatherState(error = "No data found")
                        }
                        getMessageJob?.cancel()
                    }
                    is Resource.Loading -> {
                        _loadingValueState.floatValue = result.iteration / nbRequest.toFloat()
                    }
                    else -> {//error
                        _loadingValueState.value = 1f
                        _weatherState.value = WeatherState(error = result.message ?: "Unknowing error")
                        getMessageJob?.cancel()
                    }
                }
            }
            .launchIn(viewModelScope)
    }

    fun onRefreshWeather() {
        getMessageLoading()
        getWeatherByListCityName(cities)
    }

    private fun getMessageLoading() = viewModelScope.launch(Dispatchers.IO) {
        getMessageJob?.cancel()
        getMessageJob = getMessageUseCase(loadingMessages, maxTime = 60000, refreshInterval = 6000)
            .onEach {
                _loadingMessageState.value = it
                println("getMessageLoading "+_loadingMessageState.value)
            }
            .launchIn(viewModelScope)
    }

}