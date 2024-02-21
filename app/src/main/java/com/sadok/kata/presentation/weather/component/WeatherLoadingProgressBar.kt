package com.sadok.kata.presentation.weather.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun WeatherLoadingProgressBar(
    modifier: Modifier = Modifier,
    loadingText: String,
    loadingValue: Float = 0f,
    onRestart: () -> Unit

) {


    Column(

        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        if (loadingValue < 1) {
            Text(
                style = MaterialTheme.typography.titleMedium,
                color = Color.Black,
                text = loadingText,
                minLines = 2
            )
            Spacer(modifier = Modifier.padding(8.dp))
            LinearProgressIndicator(
                progress = loadingValue,
                modifier = modifier
                    .height(30.dp)
                    .clip(RoundedCornerShape(16.dp)),
            )
        } else {
            Button(onClick = { onRestart() }) {
                Text(text = "Restart")
            }
        }
    }
}