package com.example.pulsewave_app.CurrentReadingSection

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulsewave_app.R
import com.example.pulsewave_app.ui.theme.OpenSans

@Composable
fun CurrentReadingCard(
    systolic: Int,
    diastolic: Int,
    bpStatusColor: Color,
    bpCardColor: Color,
    bpGlowColor: Color,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .height(IntrinsicSize.Max)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Box(
            modifier = Modifier
                .shadow(
                    elevation = 4.dp,
                    shape = RoundedCornerShape(20.dp),
                    spotColor = bpGlowColor,
                )
                .padding(0.dp, 4.dp)
                .background(color = bpCardColor, shape = (RoundedCornerShape(16.dp)))
                .height(IntrinsicSize.Max)
                .fillMaxSize(),
            contentAlignment = Alignment.TopCenter
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = modifier
                    .width(IntrinsicSize.Max)
                    .padding(80.dp, 16.dp)
            ) {
                if (systolic >= 0 && diastolic >= 0) {
                    /**
                     * Numerator/Systolic/Top text
                     */
                    Text(
                        modifier = modifier.padding(10.dp, 2.dp),

                        text = "$systolic",
                        style = TextStyle(
                            fontFamily = OpenSans,
                            fontWeight = FontWeight(700),
                            fontSize = 40.sp,
                        ),
                        color = bpStatusColor
                    )
                    /**
                     * This is the fraction line
                     */
                    Box(
                        modifier = modifier
                            .height(4.dp)
                            .background(
                                color = bpStatusColor,
                                shape = RoundedCornerShape(4.dp)
                            )
                            .fillMaxWidth()
                    )
                    /**
                     * Denominator/Diastolic/Bottom Text
                     */
                    Text(
                        modifier = modifier.padding(2.dp),
                        text = "$diastolic",
                        style = TextStyle(
                            fontFamily = OpenSans,
                            fontWeight = FontWeight(700),
                            fontSize = 40.sp,
                        ),
                        color = bpStatusColor
                    )
                } else {
                    Image(
                        imageVector = ImageVector.vectorResource(id = R.drawable.no_connection),
                        contentDescription = "pulse",
                    )
                }
            }
        }
    }
}



