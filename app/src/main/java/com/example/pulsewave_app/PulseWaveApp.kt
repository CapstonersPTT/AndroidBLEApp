package com.example.pulsewave_app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
//import com.example.BLE_App.data.BLEManager
import com.example.pulsewave_app.ui.theme.PulseWaveTheme
import com.example.pulsewave_app.screens.home_screen.HomeScreen
import com.example.pulsewave_app.screens.calibrate_screen.CalibrationScreen
import com.example.pulsewave_app.screens.learn_more_screen.LearnMoreScreen
import com.example.pulsewave_app.ui.components.ScreenContainer
import com.example.pulsewave_app.ui.utils.BPRange
import com.example.pulsewave_app.ui.utils.findBPRange
import kotlinx.coroutines.delay

enum class Screens { HOME, LEARNMORE, CALIBRATE }

val TAG = "PulseWaveApp.kt"

class PulseWaveApp : ComponentActivity() {
//    private lateinit var bleManager: BLEManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        bleManager = BLEManager()
//        val bloodPressure = bleManager.getBloodPressureReading()

        val initialSystolic = -1
        val initialDiastolic = -1
        var externalSystolic = 170
        var externalDiastolic = 110

        setContent {
            val (systolic, setSystolic) = remember { mutableIntStateOf(initialSystolic) }
            val (diastolic, setDiastolic) = remember { mutableIntStateOf(initialDiastolic) }
            val (openScreen, setOpenScreen) = remember { mutableStateOf(Screens.HOME) }
            val (learnMoreBpRange, setLearnMoreBpRange) = remember { mutableStateOf(BPRange.NULL) }
            fun onLearnMoreClick() {
                setLearnMoreBpRange(findBPRange(systolic, diastolic))
                setOpenScreen(Screens.LEARNMORE)
            }

            fun onCloseButtonClick() {
                setOpenScreen(Screens.HOME)
            }

            fun onCalibrationClick() {
                setOpenScreen(Screens.CALIBRATE)
            }


            PulseWaveTheme {
                ScreenContainer {
                    when (openScreen) {
                        Screens.HOME -> {
                            HomeScreen(systolic, diastolic, { onLearnMoreClick() }, { onCalibrationClick() })
                        }
                        Screens.LEARNMORE -> {
                            LearnMoreScreen(learnMoreBpRange) { onCloseButtonClick() }
                        }
                        Screens.CALIBRATE -> {
                            CalibrationScreen(setSystolic, setDiastolic) { onCloseButtonClick() }
                        }
                    }
                }
            }
            //This should later be used for updating the blood pressure based on readings.
            LaunchedEffect(Unit) {
                while(true) {
                    delay(1000)
                    externalSystolic -= 2
                    externalDiastolic -= 1
                    setSystolic(externalSystolic)
                    setDiastolic(externalDiastolic)
                }
            }
        }
    }


    @Preview(showBackground = true)
    @Composable
    fun PulseWaveAppPreview() {
        PulseWaveTheme {
            ScreenContainer {
                HomeScreen(120, 70, {}, {})
            }
        }
    }
}