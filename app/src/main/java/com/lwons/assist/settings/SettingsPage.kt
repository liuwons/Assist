package com.lwons.assist.settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.lwons.assist.settings.panel.BubbleStyle
import com.lwons.assist.settings.panel.PanelAppearance
import com.lwons.assist.settings.panel.SelectFunction

@Composable
fun SettingsPage(modifier: Modifier = Modifier) {

    Box(modifier = modifier) {
        var showPanelAppearanceSetting by remember { mutableStateOf(false) }
        var showBubbleStyleSetting by remember { mutableStateOf(false) }
        var showSelectFunction by remember { mutableStateOf(false) }

        val scrollState = rememberScrollState()
        Column(modifier = modifier
            .verticalScroll(scrollState)
            .padding(horizontal = 24.dp)) {
            // title
            Text("Settings")

            Spacer(modifier = Modifier.height(16.dp))

            // enable bubble
            Row(modifier = Modifier.fillMaxWidth()) {
                Text("Enable bubble")
                Spacer(modifier = Modifier.weight(1f))
                Switch(checked = true, onCheckedChange = {})
            }

            Spacer(modifier = Modifier.height(16.dp))

            // panel appearance
            Text("Panel appearance", modifier = Modifier.clickable { showPanelAppearanceSetting = true })

            Spacer(modifier = Modifier.height(16.dp))

            // bubble style
            Text("Bubble style", modifier = Modifier.clickable { showBubbleStyleSetting = true })

            Spacer(modifier = Modifier.height(16.dp))

            // custom action
            Column(modifier = Modifier.fillMaxWidth()) {
                Text("Custom Action")

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("Single-Tap")
                    Spacer(modifier = Modifier.weight(1f))
                    Text("Open Menu")
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("Single-Tap")
                    Spacer(modifier = Modifier.weight(1f))
                    Text("None")
                }

                Row(modifier = Modifier.fillMaxWidth()) {
                    Text("Long Press")
                    Spacer(modifier = Modifier.weight(1f))
                    Text("Hide Button")
                }
            }


        }

        if (showPanelAppearanceSetting) {
            PanelAppearance(dismiss = { showPanelAppearanceSetting = false },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp))
        }

        if (showBubbleStyleSetting) {
            BubbleStyle(dismiss = { showBubbleStyleSetting = false },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp))
        }

        if (showSelectFunction) {
            SelectFunction(dismiss = { showSelectFunction = false },
                modifier = Modifier
                    .fillMaxSize()
                    .padding(40.dp))
        }

    }



}