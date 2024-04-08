package com.example.pulsewave_app.bluetooth_low_energy

import android.app.Service
import android.bluetooth.BluetoothAdapter
import android.bluetooth.BluetoothGatt
import android.bluetooth.BluetoothGattCallback
import android.bluetooth.BluetoothGattCharacteristic
import android.bluetooth.BluetoothGattDescriptor
import android.bluetooth.BluetoothManager
import android.bluetooth.le.ScanCallback
import android.bluetooth.le.ScanResult
import android.content.Context
import android.content.Intent
import android.os.Binder
import android.os.Handler
import android.os.IBinder
import android.os.Looper
import android.util.Log
import java.util.UUID

class BluetoothScanService(private val context: Context)  {

    //Bluetooth Variables
    private val bluetoothManager =
        context.getSystemService(Context.BLUETOOTH_SERVICE) as BluetoothManager
    private var bluetoothAdapter = bluetoothManager.adapter

    private var systolic = -1;
    private var diastolic = -1;


    public fun getSystolic(): Int {
        print(systolic)
        return systolic
    }

    public fun getDiastolic(): Int {
        print(diastolic)
        return diastolic
    }


    var bluetoothGatt: BluetoothGatt? = null
    var bluetoothGattCallback = object : BluetoothGattCallback() {
        override fun onConnectionStateChange(gatt: BluetoothGatt, status: Int, newState: Int) {
            super.onConnectionStateChange(gatt, status, newState)
            if (newState == BluetoothGatt.STATE_CONNECTED) {
                Log.v("BluetoothScanService.kt", "Connected to GATT server.")
                try {
                    gatt.discoverServices()
                }catch (e: SecurityException) {
                    Log.v("BluetoothScanService.kt", "ERROR FUUUUCK " + e.message)
                    throw e
                }
            } else if (newState == BluetoothGatt.STATE_DISCONNECTED) {
                Log.v("BluetoothScanService.kt", "Disconnected from GATT server.")
            }
        }

        override fun onServicesDiscovered(gatt: BluetoothGatt, status: Int) {
            super.onServicesDiscovered(gatt, status)
            Log.v("BluetoothScanService.kt", "onServicesDiscovered")
            Log.v("BluetoothScanService.kt", "services explored status: $status")
            Log.v("BluetoothScanService.kt", "services explored: " + gatt.services)
            gatt.services.forEach{
                if (it.uuid.toString().startsWith("00001810")) {
                    Log.v("BluetoothScanService.kt", "found blood pressure service")

                    it.characteristics.forEach { characteristic ->
                        Log.v("BluetoothScanService.kt", "characteristic: " + characteristic.uuid.toString())
                        try {
                            gatt.setCharacteristicNotification(characteristic, true)
                            val descriptor = characteristic.getDescriptor(UUID.fromString("00002902-0000-1000-8000-00805f9b34fb"))
                            descriptor.value = BluetoothGattDescriptor.ENABLE_NOTIFICATION_VALUE
                            gatt.writeDescriptor(descriptor)

                            val readifiedValue = gatt.readCharacteristic(characteristic)
                            Log.v("BluetoothScanService.kt", "readifiedValue: $readifiedValue")
                        } catch (e: SecurityException) {
                            Log.v("BluetoothScanService.kt", "ERROR FUUUUCK " + e.message)
                            throw e
                        }
                    }
                }
            }

        }

        override fun onCharacteristicChanged(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic) {
            super.onCharacteristicChanged(gatt, characteristic)
            Log.v("BluetoothScanService.kt", "onCharacteristicChanged")

            with(characteristic) {
                systolic = value.to16BitUnsignedInt()
                diastolic = value.takeSecondHalf().to16BitUnsignedInt()
                Log.v("BluetoothScanService.kt", "Systolic: $systolic")
                Log.v("BluetoothScanService.kt", "Diastolic: $diastolic")
                Log.v("BluetoothScanService.kt", "ByteArray: " + value)
                Log.v("BluetoothScanService.kt", "first16Hex: " + value.toHexString())
            }
        }


        fun ByteArray.takeSecondHalf(): ByteArray {
            return this.sliceArray(IntRange(2, 3))
        }

        fun ByteArray.to16BitUnsignedInt(): Int {
            return ((this[1].toInt() shl 8) and 0xFF00) or (this[0].toInt() and 0xFF)
        }

        override fun onCharacteristicRead(
            gatt: BluetoothGatt,
            characteristic: BluetoothGattCharacteristic,
            value: ByteArray,
            status: Int
        ) {
            super.onCharacteristicRead(gatt, characteristic, value, status)
            Log.v("BluetoothScanService.kt", "onCharacteristicRead")

            with(characteristic) {
                when (status) {
                    BluetoothGatt.GATT_SUCCESS -> {

                        Log.v("BluetoothScanService.kt", "first16: " + value.toHexString())
                    }
                    BluetoothGatt.GATT_READ_NOT_PERMITTED -> {
                        Log.v("BluetoothScanService.kt", "Read not permitted for $uuid!")
                    }
                    else -> {
                        Log.v("BluetoothScanService.kt", "Characteristic read failed for $uuid, error: $status")
                    }
                }
            }
        }
    }


    fun ByteArray.toHexString(): String =
        joinToString(separator = " ", prefix = "0x") { String.format("%02X", it) }

    //The Scan Loop
    private val leScanCallback: ScanCallback = object : ScanCallback() {
        override fun onScanResult(callbackType: Int, result: ScanResult) {
            super.onScanResult(callbackType, result)
                try {
                    if (result.device.name == "Pulsewave BP Monitor"){
                        Log.v("BluetoothScanService.kt", "found PulseWave device")
                        bluetoothLeScanner?.stopScan(this)
                        bluetoothGatt = result.device.connectGatt(context, false, bluetoothGattCallback)
                        Log.v("BluetoothScanService.kt", "Services discovered: " + bluetoothGatt?.services)
                    }
                } catch (e: SecurityException) {
                    Log.v("BluetoothScanService.kt", "AAAA ERROR FUUUUCK " + e.message)
                    throw e
                }

        }
    }

    //starts scan loop
    private val bluetoothLeScanner = bluetoothAdapter?.bluetoothLeScanner
    private var scanning = false
    private val handler = Handler(Looper.getMainLooper())
    private val scanPeriod: Long = 10000

    fun scanLeDevice() {
        try {
            Log.v("BluetoothScanService.kt", "scanLeDevice")
            if (!scanning) {
                handler.postDelayed({
                    Log.v("BluetoothScanService.kt", "scanLeDevice endScan")
                    scanning = false
                    bluetoothLeScanner?.stopScan(leScanCallback)
                }, scanPeriod)
                Log.v("BluetoothScanService.kt", "scanLeDevice startScan")
                scanning = true
                bluetoothLeScanner?.startScan(leScanCallback)
            } else {
                scanning = false
                bluetoothLeScanner?.stopScan(leScanCallback)
            }
        } catch (e: SecurityException) {
            scanning = false
        }
    }





//    companion object {
//        const val REQUEST_CODE_BLUETOOTH_PERMISSION = 1001 // example value
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
//            val YOUR_SERVICE_UUID = UUID.fromString("00001810-0000-1000-8000-00805f9b34fb")
//            val YOUR_CHARACTERISTIC_UUID = UUID.fromString("00002a35-0000-1000-8000-00805f9b34fb")
//
//            val service = gatt.getService(YOUR_SERVICE_UUID)
//            val characteristic = service?.getCharacteristic(YOUR_CHARACTERISTIC_UUID)
//
//            gatt.readCharacteristic(characteristic)
//        }
//
//        override fun onCharacteristicRead(gatt: BluetoothGatt, characteristic: BluetoothGattCharacteristic, status: Int) {
//            if (status == BluetoothGatt.GATT_SUCCESS) {
//                val bloodPressure = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 0)
//                // Do something with the blood pressure reading
//            }
//        }
//    }
//
//    fun connectToDevice(deviceAddress: String) {
//        val device: BluetoothDevice? = bluetoothAdapter?.getRemoteDevice(deviceAddress)
//        if (context is Activity && ActivityCompat.checkSelfPermission(
//                context,
//                Manifest.permission.BLUETOOTH_ADMIN
//            ) != PackageManager.PERMISSION_GRANTED
//        ) {
//            ActivityCompat.requestPermissions(
//                context,
//                arrayOf(
//                    Manifest.permission.BLUETOOTH_ADMIN,
//                    Manifest.permission.ACCESS_FINE_LOCATION
//                ),
//                REQUEST_CODE_BLUETOOTH_PERMISSION
//            )
//        } else {
//            bluetoothGatt = device?.connectGatt(context, false, gattCallback)
//        }
//    }
//
//    @SuppressLint("MissingPermission")
//    fun disconnectFromDevice() {
//        bluetoothGatt?.disconnect()
//        bluetoothGatt?.close()
//        bluetoothGatt = null
//    }
}