package com.lwons.assist.action

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

@Composable
fun ActionItem(action: AssistAction, modifier: Modifier = Modifier) {
    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally) {
        Spacer(modifier = Modifier.weight(1f))
        Icon(painterResource(id = action.icon(ActionExecutor.state(action.action).value)), contentDescription = action.name)
        Spacer(modifier = Modifier.height(8.dp))
        Text(text = action.name)
        Spacer(modifier = Modifier.weight(1f))
    }
}