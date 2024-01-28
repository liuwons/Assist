package com.lwons.assist.action

import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource

@Composable
fun ActionItem(action: AssistAction) {
    Icon(painterResource(id = action.icon), contentDescription = action.name)
}