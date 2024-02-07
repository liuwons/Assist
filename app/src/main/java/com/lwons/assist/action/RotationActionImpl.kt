package com.lwons.assist.action

import android.content.Context
import android.provider.Settings


class RotationActionImpl(private val context: Context): IActionImpl {

    override fun execute(action: Action) {
        when (action) {
            Action.ACTION_ROTATION -> {
                val cur = Settings.System.getInt(context.contentResolver, Settings.System.ACCELEROMETER_ROTATION, 0)
                val newVal = if (cur == 0) 1 else 0
                Settings.System.putInt(context.contentResolver, Settings.System.ACCELEROMETER_ROTATION, newVal)
            }
            else -> {
                // do nothing
            }
        }
    }
}