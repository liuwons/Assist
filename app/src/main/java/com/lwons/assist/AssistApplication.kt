package com.lwons.assist

import android.accessibilityservice.AccessibilityService
import android.app.Application
import android.util.Log
import com.lwons.assist.action.Action
import com.lwons.assist.action.ActionConfiguration
import com.lwons.assist.action.ActionExecutor
import com.lwons.assist.action.BluetoothActionImpl
import com.lwons.assist.action.DeveloperActionImpl
import com.lwons.assist.action.GlobalActionImpl
import com.lwons.assist.action.HomeActionImpl
import com.lwons.assist.action.RotationActionImpl
import com.lwons.assist.action.SettingsActionImpl
import com.lwons.assist.action.VolumeDownActionImpl
import com.lwons.assist.action.VolumeUpActionImpl
import com.lwons.assist.action.WakeLockActionImpl
import com.lwons.assist.pref.DefaultPreferences
import com.lwons.assist.pref.GlobalPreferences

class AssistApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i("AssistApplication", "onCreate")
        GlobalPreferences.init(DefaultPreferences(this))
        ActionExecutor.run {
            register(Action.ACTION_HOME, HomeActionImpl(this@AssistApplication))
            register(Action.ACTION_SETTINGS, SettingsActionImpl(this@AssistApplication))
            register(Action.ACTION_DEVELOPER_OPTIONS, DeveloperActionImpl(this@AssistApplication))
            register(Action.ACTION_VOLUME_UP, VolumeUpActionImpl(this@AssistApplication))
            register(Action.ACTION_VOLUME_DOWN, VolumeDownActionImpl(this@AssistApplication))
            register(Action.ACTION_ROTATION, RotationActionImpl(this@AssistApplication))
            register(Action.ACTION_BLUETOOTH, BluetoothActionImpl(this@AssistApplication))
            register(Action.ACTION_KEEP_SCREEN_ON, WakeLockActionImpl(this@AssistApplication))
            register(Action.ACTION_RECENT, GlobalActionImpl(this@AssistApplication, AccessibilityService.GLOBAL_ACTION_RECENTS))
            register(Action.ACTION_LOCK, GlobalActionImpl(this@AssistApplication, AccessibilityService.GLOBAL_ACTION_LOCK_SCREEN))
            register(Action.ACTION_SCREENSHOT, GlobalActionImpl(this@AssistApplication, AccessibilityService.GLOBAL_ACTION_TAKE_SCREENSHOT))
            register(Action.ACTION_POWER, GlobalActionImpl(this@AssistApplication, AccessibilityService.GLOBAL_ACTION_POWER_DIALOG))
            register(Action.ACTION_BACK, GlobalActionImpl(this@AssistApplication, AccessibilityService.GLOBAL_ACTION_BACK))
            register(Action.ACTION_NOTIFICATION, GlobalActionImpl(this@AssistApplication, AccessibilityService.GLOBAL_ACTION_NOTIFICATIONS))
            register(Action.ACTION_QUICK_SETTINGS, GlobalActionImpl(this@AssistApplication, AccessibilityService.GLOBAL_ACTION_QUICK_SETTINGS))
        }
        ActionConfiguration.init(this)
    }
}