package com.lwons.assist.action

import android.content.Context
import android.content.Intent
import android.provider.Settings

class DeveloperActionImpl(private val context: Context): IActionImpl {
    override fun execute(action: Action) {

        context.startActivity(Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK
        })
    }
}