package com.lwons.assist.panel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Panel(dismissListener: () -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxSize()
        .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
            dismissListener() }) {

        Box(modifier = Modifier
            .width(100.dp)
            .height(100.dp)
            .background(MaterialTheme.colorScheme.background)
            .clickable { }) {
            Text(text = "Assist")
        }

    }
}