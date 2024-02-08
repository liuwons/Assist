package com.lwons.assist.action

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf


class RotationActionImpl(private val context: Context): IActionImpl {

    private val st by lazy { mutableIntStateOf(0) }

    override fun execute(action: Action) {
        when (action) {
            Action.ACTION_ROTATION -> {
                // Settings.System.putInt(context.contentResolver, Settings.System.ACCELEROMETER_ROTATION, newVal)
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