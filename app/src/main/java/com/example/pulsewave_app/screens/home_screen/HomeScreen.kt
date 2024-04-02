package com.example.pulsewave_app.screens.home_screen

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulsewave_app.R
import com.example.pulsewave_app.ui.utils.findBPRange
import com.example.pulsewave_app.ui.components.BrandedHeader
import com.example.pulsewave_app.screens.home_screen.current_reading.CurrentReading
import com.example.pulsewave_app.ui.components.ScreenContainer
import com.example.pulsewave_app.ui.components.SectionDivider
import com.example.pulsewave_app.ui.theme.PulseWaveTheme
import com.example.pulsewave_app.ui.theme.OpenSans
import com.example.pulsewave_app.ui.utils.BPRange

@Composable
fun HomeScreen(
    systolic: Int,
    diastolic: Int,
    onLearnMoreClick: () -> Unit,
) {
    val bpRange = findBPRange(systolic, diastolic)
    val (bpStatusColor, bpCardColor, bpGlowColor) = bpRange.color
    val bpTitle = bpRange.title
    Column(
        modifier = Modifier
            .verticalScroll(
                state = rememberScrollState()
            )

    ) {
        Column(
            modifier = Modifier
                .padding(24.dp, 0.dp)
        ) {


            CurrentReading(
                systolic = systolic,
                diastolic = diastolic,
                bpTitle = bpTitle,
                bpStatusColor = bpStatusColor,
                bpCardColor = bpCardColor,
                bpGlowColor = bpGlowColor
            )
            Spacer(modifier = Modifier.height(8.dp))
            if (bpRange != BPRange.NULL)
                Box(
                    modifier = Modifier
                        .clickable(onClick = onLearnMoreClick)
                ) {
                    Row {
                        Text(
                            text = "Learn More   ",
                            style = TextStyle(
                                fontFamily = OpenSans,
                                fontWeight = FontWeight(700),
                                fontSize = 18.sp,
                            ),
                            color = MaterialTheme.colorScheme.onSurface
                        )
                        Image(
                            modifier = Modifier
                                .padding(4.dp)
                                .graphicsLayer { rotationZ = 180f },
                            imageVector = ImageVector.vectorResource(id = R.drawable.chevron_left_icon),
                            contentDescription = "close",
                        )
                    }
                }
            Spacer(modifier = Modifier.height(16.dp))
        }
        SectionDivider()
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PulseWaveTheme {
        ScreenContainer {
            HomeScreen(110, 70) { }
        }
    }
}