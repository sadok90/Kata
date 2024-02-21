package com.sadok.kata.presentation.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.sadok.kata.presentation.home.HomeScreen
import com.sadok.kata.presentation.weather.WeatherScreen
import com.sadok.kata.presentation.weather.WeatherViewModel

@Composable
fun NavGraph(
    startDestination: String = NavScreen.HomeScreen.route,
    weatherViewModel: WeatherViewModel
) {
    val navController = rememberNavController()

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            modifier = Modifier.padding(it),
            navController = navController,
            startDestination = startDestination
        ) {
            composable(NavScreen.HomeScreen.route) {
                HomeScreen { navController.navigate(NavScreen.WeatherScreen.route) }
            }
            composable(NavScreen.WeatherScreen.route) {
                WeatherScreen(weatherViewModel) {
                    navController.navigate(NavScreen.HomeScreen.route) {
                        launchSingleTop = true
                        popUpTo(NavScreen.HomeScreen.route)
                    }
                }
            }
        }
    }
}