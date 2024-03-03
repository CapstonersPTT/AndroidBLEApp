package com.example.BLE_App.CurrentReadingSection

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.BLE_App.R

@Composable
fun CurrentReadingSection(systolic: Int, diastolic: Int, bpTitle: String, bpStatusColor: Color, bpCardColor: Color, bpGlowColor: Color, modifier: Modifier=Modifier) {
    Column(
        modifier = modifier
            .padding(24.dp, 20.dp)
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
        Spacer(modifier = Modifier.height(16.dp))
        CurrentReadingDetails(
            iconId = R.drawable.heart_safe,
            title = "You have a healthy blood pressure",
            items = arrayOf()
        )
        Spacer(modifier = Modifier.height(16.dp))
        CurrentReadingDetails(
            iconId = R.drawable.heart_inspect,
            title = "Common Symptoms",
            items = arrayOf("")
        )
        Spacer(modifier = Modifier.height(16.dp))
        CurrentReadingDetails(
            iconId = R.drawable.heart_plus,
            title = "Do these things",
            items = arrayOf("")
        )
        Spacer(modifier = Modifier.height(16.dp))
        CurrentReadingDetails(
            iconId = R.drawable.heart_bad,
            title = "Avoid these things",
            items = arrayOf("")
        )
    }
}