package com.example.BLE_App.CurrentReadingSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.BLE_App.R
import com.example.BLE_App.ui.theme.Neutral10
import com.example.BLE_App.ui.theme.OpenSans

@Composable
fun CurrentReadingDetails(iconId: Int, title: String, items: Array<String>, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .shadow(
                elevation = 4.dp,
                shape = RoundedCornerShape(20.dp),
            )
            .background(color = Neutral10, shape = (RoundedCornerShape(16.dp)))
            .height(IntrinsicSize.Max)
            .fillMaxSize(),
    ) {
        Column {
            Row(
                modifier = modifier
                    .fillMaxSize()
                    .padding(8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = iconId),
                    contentDescription = "pulse",
                )
                Spacer(modifier = modifier.width(8.dp))
                Text(
                    text = title,
                    style = TextStyle(
                        fontFamily = OpenSans,
                        fontWeight = FontWeight(700),
                        fontSize = 15.sp
                    ),
                    color = Color.White
                )
                /**
                 * Show dropdown icon if there are items to list
                 */
                if (items.isNotEmpty()) {
                    Spacer(modifier = modifier.width(8.dp))
                    Column(
                        modifier = modifier
                            .width(16.64.dp)
                            .height(16.64.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Image(
                            modifier = modifier.graphicsLayer { rotationZ = 180f },
                            imageVector = ImageVector.vectorResource(id = R.drawable.chevron_left_icon),
                            contentDescription = "chevron",
                        )
                    }
                }
            }
        }
    }
}