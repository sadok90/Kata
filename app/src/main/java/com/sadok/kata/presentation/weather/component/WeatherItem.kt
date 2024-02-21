package com.sadok.kata.presentation.weather.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun CityWeatherItem(
    degree: Int,
    city: String,
    description: String,
    weatherImage: String,
) {
    Card(
        shape = MaterialTheme.shapes.medium,
    ) {
        Box(modifier = Modifier.padding(16.dp)) {
            WeatherInfo(degree, city, description)
            WeatherImage(weatherImage = weatherImage, Modifier.align(Alignment.BottomEnd))
        }
    }
}

@Composable
private fun WeatherImage(weatherImage: String, modifier: Modifier) {
        AsyncImage(
            modifier = modifier.size(50.dp),
            model = weatherImage,
            contentDescription = null
        )

}

@Composable
private fun WeatherInfo(
    degree: Int,
    city: String,
    description: String,
) {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(text = city, style = MaterialTheme.typography.titleLarge)
        Text(text = "$degree°C", style = MaterialTheme.typography.headlineMedium)
        Text(text = description, style = MaterialTheme.typography.bodyLarge)
    }
}


