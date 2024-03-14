package com.example.BLE_App.data
//import android.Manifest
//import android.annotation.SuppressLint
//import android.app.Activity
//import android.bluetooth.BluetoothAdapter
//import android.bluetooth.BluetoothDevice
//import android.bluetooth.BluetoothGatt
//import android.bluetooth.BluetoothGattCallback
//import android.bluetooth.BluetoothGattCharacteristic
//import android.bluetooth.BluetoothProfile
//import android.content.Context
//import android.content.pm.PackageManager
//import androidx.core.app.ActivityCompat
//
//import java.util.UUID
//
//class BLEManager(private val context: Context) {
//
//    private val bluetoothAdapter: BluetoothAdapter? = BluetoothAdapter.getDefaultAdapter()
//    private var bluetoothGatt: BluetoothGatt? = null
//
//    companion object {
//        const val REQUEST_CODE_BLUETOOTH_PERMISSION = 1001 // example value
//        val YOUR_SERVICE_UUID = UUID.fromString("00001810-0000-1000-8000-00805f9b34fb") // Replace with your actual service UUID
//        val YOUR_CHARACTERISTIC_UUID = UUID.fromString("00002a35-0000-1000-8000-00805f9b34fb") // Replace with your actual characteristic UUID
//    }
//
//    private val gattCallback = object : BluetoothGattCallback() {
//        @SuppressLint("MissingPermission")
//        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
//            when (newState) {
//                BluetoothProfile.STATE_CONNECTED -> {
//                    gatt.discoverServices()
//                }
//                BluetoothProfile.STATE_DISCONNECTED -> {
//                    bluetoothGatt = null
//                }
//                // Handle other states if necessary
//            }
//        }
//
//        @SuppressLint("MissingPermission")
//        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
//            if (status == BluetoothGatt.GATT_SUCCESS) {
//                val service = gatt.getService(YOUR_SERVICE_UUID)
//                if (service != null) {
//                    val characteristic = service.getCharacteristic(YOUR_CHARACTERISTIC_UUID)
//                    if (characteristic != null) {
//                        gatt.readCharacteristic(characteristic)
//                    } else {
//                        // Handle case where characteristic is not found
//                        // (e.g., log error or notify user)
//                    }
//                } else {
//                    // Handle case where service is not found
//                    // (e.g., log error or notify user)
//                }
//            } else {
//                // Handle service discovery failure
//                // (e.g., log error or notify user)
//            }
//        }
//
//        override fun onCharacteristicRead(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int) {
//            if (status == BluetoothGatt.GATT_SUCCESS) {
//                // Determine the correct format for reading blood pressure data based on device documentation
//                val bloodPressure = characteristic.getIntValue(
//                    // Replace with the appropriate format based on device documentation (e.g., FORMAT_SINT16, FORMAT_UTF8)
//                    BluetoothGattCharacteristic.FORMAT_UINT16, 0
//                )
//                // Do something with the blood pressure reading
//            } else {
//                // Handle characteristic read failure
//                // (e.g., log error or notify user)
//            }
//        }
//    }
//
//    fun connectToDevice(deviceAddress: String) {
//        val device: BluetoothDevice? = bluetoothAdapter?.getRemoteDevice(deviceAddress)
//
//        if (device == null) {
//            // Handle case where device is not found
//            // (e.g., log error or notify user)
//            return
//        }
//
//        if (context is Activity && ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.BLUETOOTH_ADMIN
//            ) != PackageManager.PERMISSION_GRANTED ||
//            ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.ACCESS_FINE_LOCATION
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                context as Activity,
//                arrayOf(
//                    Manifest.permission.BLUETOOTH_ADMIN,
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                ),
//                REQUEST_CODE_BLUETOOTH_PERMISSION
//            )
//            return
//        }
//
//        bluetoothGatt = device.connectGatt(context, false, gattCallback)
//    }
//
//    @SuppressLint("MissingPermission")
//    fun disconnectFromDevice() {
//        bluetoothGatt?.disconnect()
//        bluetoothGatt?.close()
//        bluetoothGatt = null
//    }
//}
