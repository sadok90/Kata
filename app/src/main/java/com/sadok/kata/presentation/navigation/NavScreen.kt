package com.sadok.kata.presentation.navigation

sealed class NavScreen(val route : String) {
    object HomeScreen : NavScreen("home_screen")
    object WeatherScreen : NavScreen("weather_screen")
}