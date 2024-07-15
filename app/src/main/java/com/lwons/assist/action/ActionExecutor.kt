package com.lwons.assist.action

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import com.lwons.assist.log.Logger

object ActionExecutor {

    private val actionImplMap = mutableMapOf<Action, IActionImpl>()

    fun register(action: Action, impl: IActionImpl) {
        actionImplMap[action] = impl
    }

    fun execute(action: Action) {
        Logger.i("ActionExecutor", "execute: $action")
        actionImplMap[action]?.execute(action)
    }

    fun state(action: Action): State<Int> {
        return actionImplMap[action]?.state() ?: mutableIntStateOf(0)
    }
}