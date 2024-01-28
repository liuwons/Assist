package com.lwons.assist.action

object ActionExecutor {

    private val actionImplMap = mutableMapOf<AssistAction, IActionImpl>()

    fun register(action: AssistAction, impl: IActionImpl) {
        actionImplMap[action] = impl
    }

    fun execute(action: AssistAction) {
        actionImplMap[action]?.execute(action)
    }
}