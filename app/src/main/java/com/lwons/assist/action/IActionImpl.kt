package com.lwons.assist.action

import androidx.compose.runtime.State

interface IActionImpl {

    fun execute(action: Action)

    fun state(): State<Int>
}