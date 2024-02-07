package com.lwons.assist

import android.app.Application
import android.util.Log
import com.lwons.assist.action.Action
import com.lwons.assist.action.ActionConfiguration
import com.lwons.assist.action.ActionExecutor
import com.lwons.assist.action.BluetoothActionImpl
import com.lwons.assist.action.DeveloperActionImpl
import com.lwons.assist.action.HomeActionImpl
import com.lwons.assist.action.RotationActionImpl
import com.lwons.assist.action.SettingsActionImpl
import com.lwons.assist.action.VolumeActionImpl
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
            register(Action.ACTION_VOLUME_UP, VolumeActionImpl(this@AssistApplication))
            register(Action.ACTION_VOLUME_DOWN, VolumeActionImpl(this@AssistApplication))
            register(Action.ACTION_ROTATION, RotationActionImpl(this@AssistApplication))
            register(Action.ACTION_BLUETOOTH, BluetoothActionImpl(this@AssistApplication))
        }
        ActionConfiguration.init(this)
    }
}