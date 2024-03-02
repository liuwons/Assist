package com.lwons.assist.settings.panel

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
fun BubbleStyle(dismiss: () -> Unit, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        Text("Choose a type of bubble: ")
    }
}