package com.sadok.kata.presentation.weather

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.sadok.kata.domain.model.Weather
import com.sadok.kata.presentation.weather.component.CityWeatherItem
import com.sadok.kata.presentation.weather.component.WeatherAppBar
import com.sadok.kata.presentation.weather.component.WeatherLoadingProgressBar

@Composable
fun WeatherScreen(viewModel: WeatherViewModel, onNavigateToHomeScreen: () -> Unit) {
    viewModel.onRefreshWeather()

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = { WeatherAppBar(onNavigateToHomeScreen) },
        bottomBar = {
            WeatherLoadingProgressBar(
                loadingText = viewModel.loadingMessageState.value,
                loadingValue = viewModel.loadingValueState.value,
                onRestart = { viewModel.onRefreshWeather() }
            )

        }
    ) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            if (viewModel.weatherState.value.error.isNotEmpty()) {
                Text(
                    modifier =
                    Modifier
                        .align(Alignment.Center)
                        .padding(16.dp),
                    style = MaterialTheme.typography.bodyLarge,
                    color = Color.Red,
                    text = viewModel.weatherState.value.error
                )

            } else {
                ListCitiesWeather(viewModel.weatherState.value.weathers)
            }
        }

    }

}


@Composable
private fun ListCitiesWeather(cityWeathers: List<Weather>) {
    Box(modifier = Modifier.fillMaxSize()) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(cityWeathers) { cityWeather ->
                CityWeatherItem(
                    degree = cityWeather.temperature,
                    city = cityWeather.cityName,
                    description = cityWeather.description,
                    weatherImage = cityWeather.icon
                )
            }
        }
    }
}




