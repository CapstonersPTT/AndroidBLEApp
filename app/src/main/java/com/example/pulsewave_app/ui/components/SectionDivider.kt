package com.example.pulsewave_app.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pulsewave_app.ui.theme.BLETheme

@Composable
fun SectionDivider(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .height(1.dp)
            .background(color = Color(0xFF222222), shape = RectangleShape)
            .fillMaxSize()
    )
}

@Preview(showBackground = true)
@Composable
fun SectionDividerPreview() {
    val cardColors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    BLETheme {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RectangleShape,
            colors = cardColors,
        ) {
            Box(
                modifier = Modifier.padding(24.dp, 20.dp),
            ) {
                Text(text = "section 1", color = MaterialTheme.colorScheme.onSurface)
            }
            SectionDivider()
            Box(
                modifier = Modifier.padding(24.dp, 20.dp),
            ) {
                Text(text = "section 2", color = MaterialTheme.colorScheme.onSurface)
            }
            SectionDivider()
            Box(
                modifier = Modifier.padding(24.dp, 20.dp),
            ) {
                Text(text = "section 3", color = MaterialTheme.colorScheme.onSurface)
            }
            SectionDivider()
            Box(
                modifier = Modifier.padding(24.dp, 20.dp),
            ) {
                Text(text = "section 4", color = MaterialTheme.colorScheme.onSurface)
            }
        }
    }
}