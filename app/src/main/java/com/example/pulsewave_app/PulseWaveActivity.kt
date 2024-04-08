package com.example.pulsewave_app

import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.pulsewave_app.bluetooth_low_energy.BluetoothScanService
//import com.example.BLE_App.data.BLEManager
import com.example.pulsewave_app.ui.theme.PulseWaveTheme
import com.example.pulsewave_app.screens.home_screen.HomeScreen
import com.example.pulsewave_app.screens.calibrate_screen.ConfigurationScreen
import com.example.pulsewave_app.screens.learn_more_screen.LearnMoreScreen
import com.example.pulsewave_app.ui.components.ScreenContainer
import com.example.pulsewave_app.ui.utils.BPRange
import com.example.pulsewave_app.ui.utils.findBPRange
import kotlinx.coroutines.delay

enum class Screens { HOME, LEARN, CONFIGURE }

val TAG = "PulseWaveApp.kt"

class PulseWaveActivity : ComponentActivity() {
    private lateinit var bleScan: BluetoothScanService

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bleScan = BluetoothScanService(this)
        //var bleManager = BLEManager(this)

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
                setOpenScreen(Screens.LEARN)
            }

            fun onCloseButtonClick() {
                setOpenScreen(Screens.HOME)
            }

            fun onCalibrationClick() {
                setOpenScreen(Screens.CONFIGURE)
            }


            fun onConnectClick() {
                val permissionsList = arrayOf(
                    android.Manifest.permission.BLUETOOTH,
                    android.Manifest.permission.BLUETOOTH_CONNECT,
                    android.Manifest.permission.BLUETOOTH_SCAN,
                    android.Manifest.permission.BLUETOOTH_ADMIN,
                    android.Manifest.permission.ACCESS_COARSE_LOCATION,
                    android.Manifest.permission.ACCESS_FINE_LOCATION
                )

                //find first permission that DNE and request it
                permissionsList.firstOrNull {
                    ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED
                }?.let {
                    Log.v("PulseWaveActivity.kt", "Requesting permission: $it")
                    ActivityCompat.requestPermissions(this, permissionsList, 0)
                }

                permissionsList.forEach() {
                    Log.v("PulseWaveActivity.kt", "Permission: $it")
                }

                Log.v("PulseWaveActivity.kt", "Start Scan")
                Log.v("PulseWaveActivity.kt", "bleManager: $bleScan")
                bleScan.scanLeDevice()
            }


            PulseWaveTheme {
                ScreenContainer {
                    when (openScreen) {
                        Screens.HOME -> {
                            HomeScreen(
                                systolic,
                                diastolic,
                                { onLearnMoreClick() },
                                { onCalibrationClick() })
                        }

                        Screens.LEARN -> {
                            LearnMoreScreen(learnMoreBpRange) { onCloseButtonClick() }
                        }

                        Screens.CONFIGURE -> {
                            ConfigurationScreen(
                                setSystolic,
                                setDiastolic,
                                { onCloseButtonClick() },
                                { onConnectClick() })
                        }
                    }
                }
            }
            //This should later be used for updating the blood pressure based on readings.
            LaunchedEffect(bleScan) {
                while (true) {
                    println("Updating Blood Pressure")
                    setSystolic(bleScan.getSystolic())
                    setDiastolic(bleScan.getDiastolic())
                    delay(500)
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