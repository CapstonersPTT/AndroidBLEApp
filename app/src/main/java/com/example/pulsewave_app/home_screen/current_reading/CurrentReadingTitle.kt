package com.example.pulsewave_app.home_screen.current_reading

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulsewave_app.ui.utils.findBPRange
import com.example.pulsewave_app.ui.theme.BLETheme
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

@Preview(showBackground = true)
@Composable
fun CurrentReadingTitlePreview() {
    val systolic = 110
    val diastolic = 70
    val bpRange = findBPRange(systolic, diastolic)
    val (bpStatusColor) = bpRange.color
    val bpTitle = bpRange.title
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
                CurrentReadingTitle(bpStatusColor, bpTitle)
            }

        }

    }
}

