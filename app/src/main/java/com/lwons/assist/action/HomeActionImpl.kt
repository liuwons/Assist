package com.lwons.assist.action

import android.content.Context
import android.content.Intent


class HomeActionImpl(private val context: Context): IActionImpl {
    override fun execute(action: AssistAction) {
        when (action) {
            AssistAction.ACTION_HOME -> {
                context.startActivity(Intent(Intent.ACTION_MAIN).apply {
                    addCategory(Intent.CATEGORY_HOME)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK
                })
            }
            else -> {
                // do nothing
            }
        }
    }
}