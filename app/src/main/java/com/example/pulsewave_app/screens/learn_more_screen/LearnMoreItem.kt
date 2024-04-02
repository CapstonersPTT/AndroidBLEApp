package com.example.pulsewave_app.screens.learn_more_screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.ParagraphStyle
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextIndent
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.pulsewave_app.R
import com.example.pulsewave_app.ui.theme.Neutral10
import com.example.pulsewave_app.ui.theme.OpenSans
import kotlin.text.Typography.bullet

@Composable
fun LearnMoreItem(
    iconId: Int,
    title: String,
    items: Array<String>,
) {
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
        Column(
            modifier = Modifier
                .padding(12.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    imageVector = ImageVector.vectorResource(id = iconId),
                    contentDescription = "heart icon",
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = title,
                    style = TextStyle(
                        fontFamily = OpenSans,
                        fontWeight = FontWeight(700),
                        fontSize = 16.sp
                    ),
                    color = Color.White
                )
            }

            if (items.isNotEmpty()) {
                Spacer(modifier = Modifier.height(4.dp))
                Row(
                    modifier = Modifier
                        .padding(26.dp, 8.dp, 0.dp, 8.dp)

                ) {
                    Text(
                        text = buildAnnotatedString {
                            items.forEach {
                                withStyle(ParagraphStyle(
                                    textIndent = TextIndent(restLine = 34.sp)
                                )) {
                                    append(bullet)
                                    append("\t\t\t\t ")
                                    append(it)
                                }
                            }
                        },
                        style = TextStyle(
                            fontFamily = OpenSans,
                            fontWeight = FontWeight(500),
                            fontSize = 14.5.sp,
                        ),
                        color = Color.White,
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LearnMoreItemPreview() {
    val cardColors = CardDefaults.cardColors(containerColor = Color.DarkGray)


    Card(
        modifier = Modifier
            .fillMaxSize(),
        shape = RectangleShape,
        colors = cardColors,
    ) {
        Box(
            modifier = Modifier.padding(24.dp, 20.dp),
        ) {
            Column {
                LearnMoreItem(
                    iconId = R.drawable.heart_safe,
                    title = "You have a healthy blood pressure",
                    items = arrayOf()
                )
                Spacer(modifier = Modifier.height(16.dp))
                LearnMoreItem(
                    iconId = R.drawable.heart_inspect,
                    title = "Common Symptoms",
                    items = arrayOf(
                        "Coughing",
                        "Bleeding",
                        "Literally dying oh god noooo please save this poor soul"
                    )
                )
                Spacer(modifier = Modifier.height(16.dp))
                LearnMoreItem(
                    iconId = R.drawable.heart_plus,
                    title = "Do these things",
                    items = arrayOf()
                )
                Spacer(modifier = Modifier.height(16.dp))
                LearnMoreItem(
                    iconId = R.drawable.heart_bad,
                    title = "Avoid these things",
                    items = arrayOf()
                )
            }
        }
    }
}