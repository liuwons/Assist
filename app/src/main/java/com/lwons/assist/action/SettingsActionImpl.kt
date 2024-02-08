package com.lwons.assist.action

import android.content.Context
import android.content.Intent
import android.provider.Settings
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf

class SettingsActionImpl(private val context: Context): IActionImpl {
    private val st by lazy { mutableIntStateOf(0) }

    override fun execute(action: Action) {
        context.startActivity(Intent(Settings.ACTION_SETTINGS).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }

    override fun state(): State<Int> {
        return st
    }
}