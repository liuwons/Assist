package com.lwons.assist.action

import android.content.Context
import android.content.Intent
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf


class HomeActionImpl(private val context: Context): IActionImpl {
    private val st by lazy { mutableIntStateOf(0) }

    override fun execute(action: Action) {
        when (action) {
            Action.ACTION_HOME -> {
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

    override fun state(): State<Int> {
        return st
    }
}