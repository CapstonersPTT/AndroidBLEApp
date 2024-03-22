package com.example.BLE_App

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
//import com.example.BLE_App.data.BLEManager
import com.example.BLE_App.ui.theme.BLETheme
import com.example.BLE_App.CurrentReadingSection.CurrentReadingSection
import com.example.BLE_App.ui.common.BPColors
import com.example.BLE_App.ui.common.BPRanges
import com.example.BLE_App.ui.common.SectionDivider
import com.example.BLE_App.ui.common.findBPRange
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
                    Greeting(70, 40)
                }
            }
        }
    }

    @Composable
    fun Greeting(systolic: Int, diastolic: Int, modifier: Modifier = Modifier) {
        val bpRange = findBPRange(systolic, diastolic)
        val (bpStatusColor, bpCardColor, bpGlowColor) = bpRange.color
        val bpTitle = bpRange.title
        val cardColors = CardDefaults.cardColors(containerColor = Color.DarkGray)

        Card(
            modifier = modifier
                .fillMaxSize(),
            shape = RectangleShape,
            colors = cardColors,
        ) {
            BrandedHeader()
            Column(modifier=Modifier
                .verticalScroll(
                    state= rememberScrollState(),
                )
            ) {
                CurrentReadingSection(
                    systolic = systolic,
                    diastolic = diastolic,
                    bpTitle = bpTitle,
                    bpStatusColor = bpStatusColor,
                    bpCardColor = bpCardColor,
                    bpGlowColor = bpGlowColor
                )
                SectionDivider()

            }
        }
    }

    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        BLETheme {
            Greeting(1, -1)
        }
    }
}