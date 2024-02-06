package com.lwons.assist.action

import android.content.Context
import com.lwons.assist.R

object ActionConfiguration {

    val displayActions = mutableListOf<AssistAction>()

    fun init(context: Context) {
        displayActions.add(AssistAction(Action.ACTION_HOME, R.drawable.home, "Home"))
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, R.drawable.settings, "Settings"))
        displayActions.add(AssistAction(Action.ACTION_DEVELOPER_OPTIONS, R.drawable.code, "Settings"))
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, R.drawable.settings, "Settings"))
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, R.drawable.settings, "Settings"))
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, R.drawable.settings, "Settings"))
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, R.drawable.settings, "Settings"))
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, R.drawable.settings, "Settings"))
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, R.drawable.settings, "Settings"))
        displayActions.add(AssistAction(Action.ACTION_SETTINGS, R.drawable.settings, "Settings"))
    }

}