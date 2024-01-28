package com.lwons.assist.action

object ActionExecutor {

    private val actionImplMap = mutableMapOf<Action, IActionImpl>()

    fun register(action: Action, impl: IActionImpl) {
        actionImplMap[action] = impl
    }

    fun execute(action: Action) {
        actionImplMap[action]?.execute(action)
    }
}