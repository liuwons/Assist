package com.lwons.assist.action

import android.content.Context
import com.lwons.assist.R

object ActionConfiguration {

    val displayActions = mutableListOf<AssistAction>()

    fun init(context: Context) {
        displayActions.add(AssistAction(Action.ACTION_HOME, R.drawable.home, "Home"))
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, R.drawable.settings, "Settings"))
        displayActions.add(AssistAction(Action.ACTION_DEVELOPER_OPTIONS, R.drawable.code, "Developer Options"))
        displayActions.add(AssistAction(Action.ACTION_VOLUME_UP, R.drawable.volume_up, "Volume Up"))
        displayActions.add(AssistAction(Action.ACTION_VOLUME_DOWN, R.drawable.volume_down, "Volume Down"))
        displayActions.add(AssistAction(Action.ACTION_ROTATION, R.drawable.screen_rotation, "Screen Rotation"))
        displayActions.add(AssistAction(Action.ACTION_BLUETOOTH, R.drawable.bluetooth, "Bluetooth"))
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, R.drawable.settings, "Settings"))
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, R.drawable.settings, "Settings"))
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, R.drawable.settings, "Settings"))
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, R.drawable.settings, "Settings"))
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, R.drawable.settings, "Settings"))
    }

}