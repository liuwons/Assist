package com.lwons.assist.action

import android.Manifest
import android.bluetooth.BluetoothAdapter
import android.content.Context
import android.content.pm.PackageManager
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.core.app.ActivityCompat
import com.lwons.assist.log.Logger


class BluetoothActionImpl(private val context: Context): IActionImpl {
    companion object {
        private const val TAG = "BluetoothActionImpl"
    }

    private val bluetoothAdapter by lazy { BluetoothAdapter.getDefaultAdapter() }
    private val st by lazy { mutableIntStateOf(0) }

    init {
        st.intValue = if (bluetoothAdapter.isEnabled) 1 else 0
    }

    override fun execute(action: Action) {
        when (action) {
            Action.ACTION_BLUETOOTH -> {
                if (st.intValue == 1) {
                    Logger.i(TAG, "disable bluetooth")
//                    if (ActivityCompat.checkSelfPermission(context, Manifest.permission.BLUETOOTH_CONNECT) != PackageManager.PERMISSION_GRANTED) {
//                        return
//                    }
                    // bluetoothAdapter.disable()
                    st.intValue = 0
                } else {
                    Logger.i(TAG, "enable bluetooth")
                    // bluetoothAdapter.enable()
                    st.intValue = 1
                }
            }
            else -> {
                // do nothing
            }
        }
    }

    override fun state(): State<Int> {
        return st
    }
}