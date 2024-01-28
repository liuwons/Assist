package com.lwons.assist

import android.content.Context
import android.provider.Settings
import android.util.Log

object PermissionUtils {

    private fun isAccessibilityServiceEnabled(context: Context, accessibilityService: String): Boolean {
        val enabledServices = Settings.Secure.getString(context.contentResolver, Settings.Secure.ENABLED_ACCESSIBILITY_SERVICES)
        Log.i("PermissionUtils", "enabledServices: $enabledServices")
        return enabledServices?.contains(accessibilityService) == true
    }

    fun isAccessibilityServiceEnabled(context: Context): Boolean {
        return isAccessibilityServiceEnabled(context, context.packageName + "/" + AssistAccessibilityService::class.java.canonicalName)
    }

}