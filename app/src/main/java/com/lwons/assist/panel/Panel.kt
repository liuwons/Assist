package com.lwons.assist.panel

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import com.lwons.assist.action.Action
import com.lwons.assist.action.ActionConfiguration
import com.lwons.assist.action.ActionItem
import kotlin.math.min

private const val PANEL_MAX_SIZE_DP = 360
private const val GRID_SIZE = 3

@Composable
fun Panel(dismissListener: () -> Unit, actionListener: (Action) -> Unit) {
    Box(contentAlignment = Alignment.Center, modifier = Modifier
        .fillMaxSize()
        .clickable(indication = null, interactionSource = remember { MutableInteractionSource() }) {
            dismissListener()
        }) {

        val configuration = LocalConfiguration.current
        val panelSizeDp = min(PANEL_MAX_SIZE_DP, min(configuration.screenWidthDp, configuration.screenHeightDp) * 3 / 4)

        Column(modifier = Modifier
            .width(panelSizeDp.dp)
            .height(panelSizeDp.dp)
            .clip(RoundedCornerShape(12.dp))
            .background(MaterialTheme.colorScheme.background)) {

            for (i in 0 until GRID_SIZE) {
                Row(modifier = Modifier.weight(1.0f)) {
                    for (j in 0 until GRID_SIZE) {
                        val action = ActionConfiguration.displayActions[i * GRID_SIZE + j]
                        ActionItem(action, Modifier.weight(1.0f).clickable { actionListener(action.action) })
                    }
                }
            }
        }
    }
}