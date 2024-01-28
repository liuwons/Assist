package com.lwons.assist

import android.app.Application
import android.util.Log
import com.lwons.assist.action.Action
import com.lwons.assist.action.ActionConfiguration
import com.lwons.assist.action.ActionExecutor
import com.lwons.assist.action.HomeActionImpl
import com.lwons.assist.action.SettingsActionImpl
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
        }
        ActionConfiguration.init(this)
    }
}