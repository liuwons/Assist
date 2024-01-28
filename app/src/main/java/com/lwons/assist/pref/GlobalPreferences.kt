package com.lwons.assist.pref

object GlobalPreferences {

    private lateinit var impl: IAssistPreferences

    fun init(impl: IAssistPreferences) {
        this.impl = impl
    }

    fun saveString(key: String, value: String) {
        impl.saveString(key, value)
    }

    fun loadString(key: String, default: String): String {
        return impl.loadString(key, default)
    }

    fun saveInt(key: String, value: Int) {
        impl.saveInt(key, value)
    }

    fun loadInt(key: String, default: Int): Int {
        return impl.loadInt(key, default)
    }

}