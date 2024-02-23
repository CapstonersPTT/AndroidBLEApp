package com.example.BLE_App

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
//import com.example.BLE_App.data.BLEManager
import com.example.BLE_App.ui.theme.BLETheme

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




    @Composable
    fun Greeting(bloodPressure: String, modifier: Modifier = Modifier) {
        val (systolic, diastolic) = bloodPressure.split(" / ").map { it.toInt() }
        val bpStatusColor = when {
            systolic < 120 && diastolic < 80 -> Color.Green  // Good
            systolic in 120..139 || diastolic in 80..89 -> Color.hsl(24.0f, 1.0f, 0.5f) // Not great
            else -> Color.Red  // Bad
        }

        val cardColors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface)

        Card(
            modifier = modifier
                .fillMaxSize()
                .padding(8.dp),  // Add padding here
            shape = RoundedCornerShape(8.dp),
            colors = cardColors
        ) {
            Column(modifier = Modifier.padding(0.dp)) {
                Text(
                    text = "Blood Pressure Reading",
                    style = MaterialTheme.typography.headlineLarge,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Spacer(modifier = Modifier.height(8.dp))
                Text(
                    text = "$systolic / $diastolic",
                    style = MaterialTheme.typography.headlineMedium,
                    color = bpStatusColor
                )
            }
        }
    }




    @Preview(showBackground = true)
    @Composable
    fun GreetingPreview() {
        BLETheme {
            Greeting("120 / 80")
        }
    }
}