package com.lwons.assist.touch

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun Entry(onRequestTranslate: (Int, Int) -> Unit, onRequestFinished: () -> Unit, modifier: Modifier = Modifier) {
    Bubble(onRequestTranslate = onRequestTranslate, onRequestFinished = onRequestFinished, modifier = modifier.width(64.dp).height(64.dp))
}