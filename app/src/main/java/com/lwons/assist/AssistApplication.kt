package com.lwons.assist

import android.app.Application
import android.util.Log
import com.lwons.assist.pref.DefaultPreferences
import com.lwons.assist.pref.GlobalPreferences

class AssistApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        Log.i("AssistApplication", "onCreate")
        GlobalPreferences.init(DefaultPreferences(this))
    }
}