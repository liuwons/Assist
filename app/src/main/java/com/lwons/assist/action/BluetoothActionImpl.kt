package com.lwons.assist.action

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat


class BluetoothActionImpl(private val context: Context): IActionImpl {
    private val bluetoothAdapter by lazy { BluetoothAdapter.getDefaultAdapter() }

    override fun execute(action: Action) {
        when (action) {
            Action.ACTION_BLUETOOTH -> {
                if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return
                }
                if (bluetoothAdapter.isEnabled) {
                    bluetoothAdapter.disable()
                } else {
                    bluetoothAdapter.enable()
                }
            }
            else -> {
                // do nothing
            }
        }
    }
}