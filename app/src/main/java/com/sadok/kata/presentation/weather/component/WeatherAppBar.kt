package com.sadok.kata.presentation.weather.component

import androidx.compose.foundation.layout.statusBarsPadding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.sadok.kata.utils.Constants


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeatherAppBar(onBackClick: () -> Unit) {
    TopAppBar(
        modifier = Modifier.statusBarsPadding(),
        title = { Text(text = Constants.TOPBAR_TITLE, style = MaterialTheme.typography.headlineMedium) },
        navigationIcon = {
            IconButton(onClick = onBackClick) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "back button",
                )
            }
        }
    )
}