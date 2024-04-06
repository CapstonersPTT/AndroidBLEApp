package com.example.pulsewave_app.screens.home_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import com.example.pulsewave_app.screens.home_screen.current_reading.CurrentReading
import com.example.pulsewave_app.screens.home_screen.current_reading.LearnMoreButton
import com.example.pulsewave_app.screens.home_screen.historic_data.ChartSection
import com.example.pulsewave_app.ui.components.ScreenContainer
import com.example.pulsewave_app.ui.components.SectionDivider
import com.example.pulsewave_app.ui.theme.OpenSans
import com.example.pulsewave_app.ui.theme.PulseWaveTheme
import com.example.pulsewave_app.ui.utils.BPRange

@Composable
fun HomeScreen(
    systolic: Int,
    diastolic: Int,
    onLearnMoreClick: () -> Unit,
    onSettingsClick: () -> Unit
) {
    val bpRange = findBPRange(systolic, diastolic)
    val (bpStatusColor, bpCardColor, bpGlowColor) = bpRange.color
    val bpTitle = bpRange.title
    Column {
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
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "Home",
                    style = TextStyle(
                        fontFamily = OpenSans,
                        fontWeight = FontWeight(700),
                        fontSize = 24.sp
                    ),
                    color = Color.White
                )

                CurrentReading(
                    systolic = systolic,
                    diastolic = diastolic,
                    bpTitle = bpTitle,
                    bpStatusColor = bpStatusColor,
                    bpCardColor = bpCardColor,
                    bpGlowColor = bpGlowColor
                )
                if (bpRange != BPRange.NULL)
                    LearnMoreButton(onClick = onLearnMoreClick)
                Spacer(modifier = Modifier.height(16.dp))
            }
            SectionDivider()
            Spacer(modifier = Modifier.height(16.dp))
            ChartSection()
        }
        Spacer(modifier = Modifier.weight(1f))
        SectionDivider()
        //settings
        Row(
            modifier = Modifier
                .padding(8.dp),
        ) {
            Spacer(modifier = Modifier.weight(1f))
            Box(modifier = Modifier.clickable { onSettingsClick() }) {
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.settings_gear),
                    contentDescription = "settings",
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    PulseWaveTheme {
        ScreenContainer {
            HomeScreen(110, 70, {}, {})
        }
    }
}