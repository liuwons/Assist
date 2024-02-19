package com.lwons.assist

import android.annotation.SuppressLint
import com.lwons.assist.log.Logger

object GlobalService {
    private const val TAG = "GlobalService"

    @SuppressLint("StaticFieldLeak")
    private var service: AssistAccessibilityService? = null

    fun init(service: AssistAccessibilityService) {
        this.service = service
    }

    fun destroy() {
        service = null
    }

    fun performAction(action: Int) {
        Logger.i(TAG, "performAction: $action")
        service?.performGlobalAction(action)
    }

}