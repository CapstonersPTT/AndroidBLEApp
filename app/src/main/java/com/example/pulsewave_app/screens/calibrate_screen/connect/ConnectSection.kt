package com.example.pulsewave_app.screens.calibrate_screen.connect

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulsewave_app.ui.theme.OpenSans

@Composable
fun ConnectSection(onConnect: () -> Unit) {
    Column(
    modifier = Modifier
    .padding(24.dp, 0.dp)
    ) {
        Row(){
            Spacer(modifier = Modifier.width(16.dp))
            Text(
                text = "Connect",
                style = TextStyle(
                    fontFamily = OpenSans,
                    fontWeight = FontWeight(700),
                    fontSize = 20.sp,
                ),
                color = Color.White
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Row() {
            Spacer(modifier = Modifier.weight(1f))
            Box(
                modifier = Modifier
                    .clickable {
                        onConnect()
                    }
                    .background(color = Color.White, shape = (RoundedCornerShape(50)))
                    .padding(64.dp, 8.dp)
            ) {
                Text(
                    text = "F I N D   D E V I C E",
                    style = TextStyle(
                        fontFamily = OpenSans,
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp,
                    ),
                    color = Color.DarkGray
                )
            }
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}