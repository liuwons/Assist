package com.lwons.assist.pref

import android.content.Context

class DefaultPreferences(private val appContext: Context): IAssistPreferences {

    companion object {
        private const val PREF_NAME = "global_pref"
    }

    override fun saveString(key: String, value: String) {
        appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit().putString(key, value).apply()
    }

    override fun loadString(key: String, default: String): String {
        return appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getString(key, default) ?: default
    }

    override fun saveInt(key: String, value: Int) {
        appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).edit().putInt(key, value).apply()
    }

    override fun loadInt(key: String, default: Int): Int {
        return appContext.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE).getInt(key, default)
    }
}