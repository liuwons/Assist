package com.lwons.assist.action

import android.content.Context
import android.content.Intent
import android.provider.Settings

class SettingsActionImpl(private val context: Context): IActionImpl {
    override fun execute(action: AssistAction) {
        context.startActivity(Intent(Settings.ACTION_SETTINGS).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}