package com.example.BLE_App.data

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothDevice
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothProfile
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


import java.util.UUID

class BLEManager(private val context: Context) {
    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
    private var bluetoothGatt: BluetoothGatt? = null
    private var systolicReading: Int? = null
    private var diastolicReading: Int? = null

    companion object {
        const val REQUEST_CODE_BLUETOOTH_PERMISSION = 1001 // example value
        val YOUR_SERVICE_UUID =
            UUID.fromString("00001810-0000-1000-8000-00805f9b34fb") // Replace with your actual service UUID
        val BLOOD_PRESSURE_CHARACTERISTIC_UUID =
            UUID.fromString("00002a35-0000-1000-8000-00805f9b34fb") // Replace with your blood pressure characteristic UUID (if known)
    }


    private val gattCallback = object : BluetoothGattCallback() {
        @SuppressLint("MissingPermission")
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            when (newState) {
                BluetoothProfile.STATE_CONNECTED -> {
                    gatt.discoverServices()
                }

                BluetoothProfile.STATE_DISCONNECTED -> {
                    bluetoothGatt = null
                    systolicReading = null
                    diastolicReading = null
                }
                // Handle other states if necessary
            }
        }
        @SuppressLint("MissingPermission")
        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                val service = gatt.getService(YOUR_SERVICE_UUID)
                if (service != null) {
                    val characteristic =
                        service.getCharacteristic(BLOOD_PRESSURE_CHARACTERISTIC_UUID)
                    if (characteristic != null) {
                        gatt.readCharacteristic(characteristic)
                    } else {
                        // Handle case where characteristic is not found
                        // (e.g., log error or notify user)
                    }
                } else {
                    // Handle case where service is not found
                    // (e.g., log error or notify user)
                }
            } else {
                // Handle service discovery failure
                // (e.g., log error or notify user)
            }
        }
        @Deprecated("Deprecated in Java")
        override fun onCharacteristicRead(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            status: Int
        ) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                val uuid = characteristic.uuid
                if (uuid.equals(BLOOD_PRESSURE_CHARACTERISTIC_UUID)) {
                    // Assuming both systolic and diastolic are packed together in the same characteristic
                    val systolicReading =
                        characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0)
                    val diastolicReading =
                        characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 2)
                } else {
                    // Handle reading from unknown characteristic
                }
            } else {
                // Handle characteristic read failure
                // (e.g., log error or notify user)
            }
        }
    }


    fun connectToDevice(deviceAddress: String) {
        val device: BluetoothDevice? = bluetoothAdapter?.getRemoteDevice(deviceAddress)

        if (device == null) {
            // Handle case where device is not found
            // (e.g., log error or notify user)
            return
        }

        if (context is Activity && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED ||
            ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context as Activity,
                arrayOf(
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                REQUEST_CODE_BLUETOOTH_PERMISSION
            )
            return
        }

        bluetoothGatt = device.connectGatt(context, false, gattCallback)
    }

    /**
     * Attempts to read systolic and diastolic blood pressure values from the connected device.
     *
     * @return An array of two integers representing systolic and diastolic blood pressure readings,
     * or an array of two zeros (0) if the operation fails or the device is not connected.
     */
    fun getBloodPressureReading(): IntArray {
        // Check if the device is connected and the readings are available
        if (bluetoothGatt != null || systolicReading != null || diastolicReading != null) {
            val readings = intArrayOf(systolicReading!!, diastolicReading!!)
            systolicReading = null
            diastolicReading = null
            return readings
        }

        // If the device is not connected or the readings are not available, return (0, 0)
        return intArrayOf(0, 0)
    }
}