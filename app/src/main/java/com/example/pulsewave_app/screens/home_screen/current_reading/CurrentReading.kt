package com.example.pulsewave_app.screens.home_screen.current_reading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.pulsewave_app.ui.utils.findBPRange
import com.example.pulsewave_app.ui.theme.PulseWaveTheme

@Composable
fun CurrentReading(
    systolic: Int,
    diastolic: Int,
    bpTitle: String,
    bpStatusColor: Color,
    bpCardColor: Color,
    bpGlowColor: Color,
) {
    Column(
        modifier = Modifier
            .padding(0.dp, 20.dp)
            .height(IntrinsicSize.Max),
    ) {
        CurrentReadingTitle(bpStatusColor = bpStatusColor, bpTitle = bpTitle)
        Spacer(modifier = Modifier.height(4.dp))
        CurrentReadingCard(
            systolic = systolic,
            diastolic = diastolic,
            bpStatusColor = bpStatusColor,
            bpCardColor = bpCardColor,
            bpGlowColor = bpGlowColor
        )
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentReadingPreview() {
    val systolic = 110
    val diastolic = 70
    val bpRange = findBPRange(systolic, diastolic)
    val (bpStatusColor, bpCardColor, bpGlowColor) = bpRange.color
    val bpTitle = bpRange.title
    val cardColors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    PulseWaveTheme {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RectangleShape,
            colors = cardColors,
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(24.dp, 0.dp),
            ) {
                CurrentReading(
                    systolic,
                    diastolic,
                    bpTitle,
                    bpStatusColor,
                    bpCardColor,
                    bpGlowColor
                )

            }
        }
    }
}