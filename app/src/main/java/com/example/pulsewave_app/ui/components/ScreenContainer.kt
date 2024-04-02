package com.example.pulsewave_app.ui.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape

@Composable
fun ScreenContainer(
    composable: @Composable (() -> Unit )? = null
){
    val cardColors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    Surface(modifier = Modifier.fillMaxSize()) {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RectangleShape,
            colors = cardColors,
        ) {
            BrandedHeader()
            if (composable != null) {
                composable()
            }
        }
    }
}

