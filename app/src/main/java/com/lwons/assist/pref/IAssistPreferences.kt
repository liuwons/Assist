package com.lwons.assist.pref

interface IAssistPreferences {

    fun saveString(key: String, value: String)

    fun loadString(key: String, default: String): String

    fun saveInt(key: String, value: Int)

    fun loadInt(key: String, default: Int): Int
}