package com.lwons.assist.log

import android.util.Log

object Logger {
    private var level: LogLevel = LogLevel.DEBUG

    fun d(tag: String, msg: String) {
        if (level == LogLevel.DEBUG) {
            Log.d(tag, msg)
        }
    }

    fun i(tag: String, msg: String) {
        if (level >= LogLevel.INFO) {
            Log.i(tag, msg)
        }
    }

    fun w(tag: String, msg: String) {
        if (level >= LogLevel.WARN) {
            Log.w(tag, msg)
        }
    }

    fun e(tag: String, msg: String) {
        if (level >= LogLevel.ERROR) {
            Log.e(tag, msg)
        }
    }

}

enum class LogLevel(value: Int) {
    DEBUG(0),
    INFO(1),
    WARN(2),
    ERROR(3)

}