package com.example.pulsewave_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
//import com.example.BLE_App.data.BLEManager
import com.example.pulsewave_app.ui.theme.BLETheme
import com.example.pulsewave_app.home_screen.HomeScreen


class MainActivity : ComponentActivity() {
//    private lateinit var bleManager: BLEManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        bleManager = BLEManager()

        setContent {
            BLETheme {
                Surface(modifier = Modifier.fillMaxSize()) {
//                    val bloodPressure = bleManager.getBloodPressureReading()
                    HomeScreen(70, 40)
                }
            }
        }
    }



    @Preview(showBackground = true)
    @Composable
    fun AppPreview() {
        BLETheme {
                HomeScreen(120, 70 )
        }
    }
}