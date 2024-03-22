package com.example.pulsewave_app.data

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

    companion object {
        const val REQUEST_CODE_BLUETOOTH_PERMISSION = 1001 // example value
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
                }
                // Handle other states if necessary
            }
        }

        @SuppressLint("MissingPermission")
        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            val YOUR_SERVICE_UUID = UUID.fromString("00001810-0000-1000-8000-00805f9b34fb")
            val YOUR_CHARACTERISTIC_UUID = UUID.fromString("00002a35-0000-1000-8000-00805f9b34fb")

            val service = gatt.getService(YOUR_SERVICE_UUID)
            val characteristic = service?.getCharacteristic(YOUR_CHARACTERISTIC_UUID)

            gatt.readCharacteristic(characteristic)
        }

        override fun onCharacteristicRead(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int) {
            if (status == BluetoothGatt.GATT_SUCCESS) {
                val bloodPressure = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0)
                // Do something with the blood pressure reading
            }
        }
    }

    fun connectToDevice(deviceAddress: String) {
        val device: BluetoothDevice? = bluetoothAdapter?.getRemoteDevice(deviceAddress)
        if (context is Activity && ActivityCompat.checkSelfPermission(
                context,
                Manifest.permission.BLUETOOTH_ADMIN
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                context,
                arrayOf(
                    Manifest.permission.BLUETOOTH_ADMIN,
                    Manifest.permission.ACCESS_FINE_LOCATION
                ),
                REQUEST_CODE_BLUETOOTH_PERMISSION
            )
        } else {
            bluetoothGatt = device?.connectGatt(context, false, gattCallback)
        }
    }

    @SuppressLint("MissingPermission")
    fun disconnectFromDevice() {
        bluetoothGatt?.disconnect()
        bluetoothGatt?.close()
        bluetoothGatt = null
    }
}