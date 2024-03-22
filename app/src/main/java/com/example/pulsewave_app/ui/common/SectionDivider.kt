package com.example.pulsewave_app.ui.common

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.unit.dp

@Composable
fun SectionDivider(modifier: Modifier =Modifier){
    Box(
        modifier = modifier
            .height(1.dp)
            .background(color = Color(0xFF222222), shape = RectangleShape)
            .fillMaxSize()
    )
}
