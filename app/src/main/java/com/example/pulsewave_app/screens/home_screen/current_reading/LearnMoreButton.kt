package com.example.pulsewave_app.screens.home_screen.current_reading

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulsewave_app.R
import com.example.pulsewave_app.ui.theme.OpenSans

@Composable
fun LearnMoreButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clickable(onClick = onClick)
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
}