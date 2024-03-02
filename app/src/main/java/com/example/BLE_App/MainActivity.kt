package com.example.BLE_App

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import com.example.BLE_App.data.BLEManager
import com.example.BLE_App.ui.theme.BLETheme
import com.example.BLE_App.ui.theme.OpenSans
import androidx.compose.ui.unit.sp
import com.example.BLE_App.ui.theme.*


class MainActivity : ComponentActivity() {
//    private lateinit var bleManager: BLEManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        bleManager = BLEManager()

        setContent {
            BLETheme {
                Surface(modifier = Modifier.fillMaxSize()) {
//                    val bloodPressure = bleManager.getBloodPressureReading()
//                    Greeting(bloodPressure)
                    Greeting("70 / 40")
                }
            }
        }
    }


    data class BPColors(val status: Color, val card: Color, val glow: Color)

    enum class BPRanges(val color: BPColors, val title: String) {
        HYPOTENSIONALERT(
            BPColors(
                BloodPressurePurple,
                BloodPressureCardPurple,
                BloodPressureGlowPurple
            ),
            "Critical Hypotension"
        ),
        HYPOTENSION(
            BPColors(
                BloodPressurePurple,
                BloodPressureCardPurple,
                BloodPressureGlowPurple
            ),
            "Hypotensive"
        ),
        LOW(BPColors(BloodPressureBlue, BloodPressureCardBlue, BloodPressureGlowBlue), "Low"),
        NORMAL(BPColors(BloodPressureGreen, BloodPressureCardGreen, BloodPressureGlowGreen), "Normal"),
        ELEVATED(BPColors(BloodPressureYellow, BloodPressureCardYellow, BloodPressureGlowYellow), "Elevated"),
        HYPERTENSION1(
            BPColors(
                BloodPressureOrange,
                BloodPressureCardOrange,
                BloodPressureGlowOrange
            ), "Hypertensive"
        ),
        HYPERTENSION2(BPColors(BloodPressureRed, BloodPressureCardRed, BloodPressureGlowRed), "Very Hypertensive"),
        HYPERTENSIONALERT(BPColors(BloodPressureRed, BloodPressureCardRed, BloodPressureGlowRed), "Critical Hypertension")
    }

    /**
     * This function references the following 2 charts:
     * https://www.helpguide.org/articles/healthy-living/blood-pressure-and-your-brain.htm
     * https://pharmeasy.in/blog/low-blood-pressure-precautions-and-ways-to-manage-it/
     * But also, many other charts use 110 as the threshold for diastolic so I'm erring on the side
     * of safety.
     */
    private fun findBPRange(systolic: Int, diastolic: Int): BPRanges {
        return if (systolic < 80 || diastolic < 50) {
            BPRanges.HYPOTENSIONALERT
        } else if (systolic < 90 || diastolic < 60) {
            BPRanges.HYPOTENSION
        } else if (systolic < 100 || diastolic < 65) {
            BPRanges.LOW
        } else if (systolic > 180 || diastolic > 110) {
            BPRanges.HYPERTENSIONALERT
        } else if (systolic > 140 || diastolic > 90) {
            BPRanges.HYPERTENSION2
        } else if (systolic < 120 && diastolic < 80) {
            BPRanges.NORMAL
        } else if (systolic < 130 && diastolic < 80) {
            BPRanges.ELEVATED
        } else {
            BPRanges.HYPERTENSION1
        }
    }


    @Composable
    fun Greeting(bloodPressure: String, modifier: Modifier = Modifier) {
        val (systolic, diastolic) = bloodPressure.split(" / ").map { it.toInt() }
        val bpRange = findBPRange(systolic, diastolic)
        val (bpStatusColor, bpCardColor, bpGlowColor) = bpRange.color
        val bpTitle = bpRange.title

        val cardColors = CardDefaults.cardColors(containerColor = Color.DarkGray)

        Card(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),  // Add padding here
            shape = RoundedCornerShape(8.dp),
            colors = cardColors,
        ) {

            Row(modifier = modifier
                .padding(16.dp), verticalAlignment = Alignment.CenterVertically){
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.pulse_screen),
                    contentDescription = "pulse",
                    tint = BloodPressureRed
                )
                Spacer(
                    modifier=modifier.width(16.dp)
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
            Box(
                modifier=modifier
                    .height(1.dp)
                    .background(color=Color(0xFF222222), shape= RectangleShape)
                    .fillMaxSize()
            ){}
            Column(
                modifier = modifier
                    .padding(24.dp, 20.dp)
                    .height(IntrinsicSize.Max),
                ) {

                Row(modifier = modifier.height(IntrinsicSize.Max).fillMaxSize()) {
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
                Spacer(modifier = Modifier.height(4.dp))
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

                            Box(
                                modifier = modifier
                                    .height(4.dp)
                                    .background(
                                        color = bpStatusColor,
                                        shape = RoundedCornerShape(4.dp)
                                    )
                                    .fillMaxWidth()
                            )
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
                        }
                    }
                }
            }
            Box(
                modifier=modifier
                    .height(1.dp)
                    .background(color=Color(0xFF222222), shape= RectangleShape)
                    .fillMaxSize()
            ){}
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        BLETheme {
            Greeting("181 / 80")
        }
    }
}