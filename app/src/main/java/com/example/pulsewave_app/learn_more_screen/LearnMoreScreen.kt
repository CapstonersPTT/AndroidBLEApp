package com.example.pulsewave_app.learn_more_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
import com.example.pulsewave_app.ui.components.BrandedHeader
import com.example.pulsewave_app.ui.theme.BLETheme
import com.example.pulsewave_app.ui.theme.OpenSans
import com.example.pulsewave_app.ui.utils.BPRange

@Composable
fun CurrentReading(
    bpRange: BPRange
) {
    val cardColors = CardDefaults.cardColors(containerColor = Color.DarkGray)
    val bpTitle = bpRange.title
    val bpStatusColor = bpRange.color.status
    val symptoms = bpRange.symptoms
    val care = bpRange.care
    val avoid = bpRange.avoid
    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RectangleShape,
        colors = cardColors,
    ) {
        BrandedHeader()
        Column(
            modifier = Modifier
                .padding(24.dp, 20.dp)
                .height(IntrinsicSize.Max),
        ) {
            Row {
                Text(
                    text = "Learn More",
                    style = TextStyle(
                        fontFamily = OpenSans,
                        fontWeight = FontWeight(700),
                        fontSize = 25.sp
                    ),
                    color = Color.White
                )
                Spacer(Modifier.weight(1f))
                Image(
                    imageVector = ImageVector.vectorResource(id = R.drawable.close),
                    contentDescription = "close",
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            Row {
                Text(
                    text = " Your blood pressure is: ",
                    style = TextStyle(
                        fontFamily = OpenSans,
                        fontWeight = FontWeight(700),
                        fontSize = 15.sp
                    ),
                    color = Color.White
                )
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

            if(symptoms.isEmpty() && care.isEmpty() && avoid.isEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                LearnMoreItem(
                    iconId = R.drawable.heart_safe,
                    title = "You have a healthy blood pressure",
                    items = arrayOf()
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            if(symptoms.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                LearnMoreItem(
                    iconId = R.drawable.heart_inspect,
                    title = "Common Symptoms",
                    items = symptoms
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            if(care.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                LearnMoreItem(
                    iconId = R.drawable.heart_plus,
                    title = "Do these things",
                    items = care
                )
                Spacer(modifier = Modifier.height(8.dp))
            }
            if(avoid.isNotEmpty()) {
                Spacer(modifier = Modifier.height(8.dp))
                LearnMoreItem(
                    iconId = R.drawable.heart_bad,
                    title = "Avoid these things",
                    items = avoid
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CurrentReadingPreview() {
    val bpRange = BPRange.HYPOTENSIONALERT
    BLETheme {
        CurrentReading(bpRange)
    }
}