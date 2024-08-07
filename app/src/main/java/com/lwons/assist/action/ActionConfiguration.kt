package com.lwons.assist.action

import android.content.Context
import com.lwons.assist.R

object ActionConfiguration {

    val displayActions = mutableListOf<AssistAction>()

    fun init(context: Context) {
        displayActions.add(AssistAction(Action.ACTION_HOME, "Home") { R.drawable.home })
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, "Settings") { R.drawable.settings })
        displayActions.add(AssistAction(Action.ACTION_DEVELOPER_OPTIONS, "Developer") { R.drawable.code })
        displayActions.add(AssistAction(Action.ACTION_RECENT, "Recent") { R.drawable.stack })
        displayActions.add(AssistAction(Action.ACTION_LOCK, "Lock") { R.drawable.lock })
        displayActions.add(AssistAction(Action.ACTION_SCREENSHOT, "Screenshot") { R.drawable.screenshot })
        displayActions.add(AssistAction(Action.ACTION_BACK, "Back") { R.drawable.arrow_back })
        displayActions.add(AssistAction(Action.ACTION_KEEP_SCREEN_ON, "Screen On") { state -> if (state == 0) R.drawable.bright else R.drawable.bright_active})
        displayActions.add(AssistAction(Action.ACTION_POWER, "Power") { R.drawable.power_settings_new })
        displayActions.add(AssistAction(Action.ACTION_NOTIFICATION, "Notification") { R.drawable.notifications })
        displayActions.add(AssistAction(Action.ACTION_QUICK_SETTINGS, "Quick Settings") { R.drawable.toggle_on })
        displayActions.add(AssistAction(Action.ACTION_ROTATION, "Screen Rotation") { R.drawable.screen_rotation })
        displayActions.add(AssistAction(Action.ACTION_BLUETOOTH, "Bluetooth") { state -> if (state == 1) R.drawable.bluetooth else R.drawable.bluetooth_disabled })
        displayActions.add(AssistAction(Action.ACTION_VOLUME_UP, "Volume Up") { R.drawable.volume_up })
        displayActions.add(AssistAction(Action.ACTION_VOLUME_DOWN, "Volume Down") { R.drawable.volume_down })
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, "Settings") { R.drawable.settings })
    }

}