package com.example.pulsewave_app.ui.components

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulsewave_app.R
import com.example.pulsewave_app.home_screen.HomeScreen
import com.example.pulsewave_app.ui.theme.BLETheme
import com.example.pulsewave_app.ui.theme.BloodPressureRed
import com.example.pulsewave_app.ui.theme.OpenSans

@Composable
fun BrandedHeader(modifier: Modifier =Modifier){
    Row(
        modifier = modifier
            .padding(16.dp), verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = ImageVector.vectorResource(id = R.drawable.pulse_screen),
            contentDescription = "pulse",
            tint = BloodPressureRed
        )
        Spacer(
            modifier = modifier.width(16.dp)
        )
        Text(
            text = "P U L S E W A V E",
            style = TextStyle(
                fontFamily = OpenSans,
                fontWeight = FontWeight(500),
                fontSize = 36.sp,
            ),
            color = BloodPressureRed
        )
    }
    SectionDivider()
}

@Preview(showBackground = true)
@Composable
fun BrandedHeaderPreview() {
    val cardColors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    BLETheme {
        Card(
            modifier = Modifier
                .fillMaxSize(),
            shape = RectangleShape,
            colors = cardColors,
        ) {
            BrandedHeader()
        }
    }
}