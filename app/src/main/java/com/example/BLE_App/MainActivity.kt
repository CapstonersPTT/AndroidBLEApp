package com.example.BLE_App

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
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
                    Greeting("180 / 90")
                }
            }
        }
    }


    data class BPColors(val status: Color, val card: Color, val glow: Color)

    enum class BPRanges(val color: BPColors) {
        HYPOTENSIONALERT(
            BPColors(
                BloodPressurePurple,
                BloodPressureCardPurple,
                BloodPressureGlowPurple
            )
        ),
        HYPOTENSION(
            BPColors(
                BloodPressurePurple,
                BloodPressureCardPurple,
                BloodPressureGlowPurple
            )
        ),
        LOW(BPColors(BloodPressureBlue, BloodPressureCardBlue, BloodPressureGlowBlue)),
        NORMAL(BPColors(BloodPressureGreen, BloodPressureCardGreen, BloodPressureGlowGreen)),
        ELEVATED(BPColors(BloodPressureYellow, BloodPressureCardYellow, BloodPressureGlowYellow)),
        HYPERTENSION1(
            BPColors(
                BloodPressureOrange,
                BloodPressureCardOrange,
                BloodPressureGlowOrange
            )
        ),
        HYPERTENSION2(BPColors(BloodPressureRed, BloodPressureCardRed, BloodPressureGlowRed)),
        HYPERTENSIONALERT(BPColors(BloodPressureRed, BloodPressureCardRed, BloodPressureGlowRed))
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
        } else if (systolic > 160 || diastolic > 90) {
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
        val (bpStatusColor, bpCardColor, bpGlowColor) = findBPRange(systolic, diastolic).color

        val cardColors = CardDefaults.cardColors(containerColor = Color.DarkGray)

        Card(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),  // Add padding here
            shape = RoundedCornerShape(8.dp),
            colors = cardColors,
        ) {

            Column(
                modifier = modifier
                    .padding(30.dp)
                    .fillMaxSize(),

                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Live Blood Pressure",
                    style = TextStyle(
                        fontFamily = OpenSans,
                        fontWeight = FontWeight(700),
                        fontSize = 26.sp,
                    ),
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Box(
                    modifier = Modifier
                        .shadow(
                            elevation = 4.dp,
                            shape = RoundedCornerShape(20.dp),
                            spotColor = bpGlowColor,
                        )
                        .padding(4.dp)
                        .background(color = bpCardColor, shape = (RoundedCornerShape(16.dp)))
                        .height(IntrinsicSize.Max)

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
                                .height(5.dp)
                                .background(
                                    color = bpStatusColor,
                                    shape = RoundedCornerShape(3.dp)
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
    }


    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        BLETheme {
            Greeting("110 / 70")
        }
    }
}