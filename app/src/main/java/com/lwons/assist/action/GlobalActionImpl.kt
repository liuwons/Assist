package com.lwons.assist.action

import android.content.Context
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import com.lwons.assist.GlobalService

class GlobalActionImpl(private val context: Context, private val act: Int): IActionImpl {
    private val st by lazy { mutableIntStateOf(0) }

    override fun execute(action: Action) {
        GlobalService.performAction(act)
    }

    override fun state(): State<Int> {
        return st
    }
}