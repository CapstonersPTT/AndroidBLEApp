package com.example.pulsewave_app.CurrentReadingSection

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.pulsewave_app.ui.theme.OpenSans

@Composable
fun CurrentReadingTitle(bpStatusColor: Color, bpTitle: String, modifier: Modifier = Modifier) {
    Row(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .fillMaxSize()
    ) {
        Text(
            text = "Live Blood Pressure: ",
            style = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight(700),
                fontSize = 15.sp,
            ),
            color = MaterialTheme.colorScheme.onSurface
        )
        Spacer(modifier.weight(1f))
        Text(
            text = bpTitle,
            style = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight(700),
                fontSize = 15.sp,
            ),
            color = bpStatusColor
        )

    }
}