package com.lwons.assist.settings.panel

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.lwons.assist.R
import com.lwons.assist.action.ActionExecutor

private val panelCustomizers = arrayListOf(
    PanelCustomizer("NineGridPanel", { Icon(painterResource(id = R.drawable.apps), contentDescription = null) }, { NineGridPanelCustomizer() }),
    PanelCustomizer("CirclePanel", { Icon(painterResource(id = R.drawable.atr), contentDescription = null) }, { CirclePanelCustomizer() }),
)

@Composable
fun PanelAppearance(dismiss: () -> Unit, modifier: Modifier = Modifier) {
    Column(modifier = modifier) {
        val selectedPanelCustomizer by remember { mutableStateOf(panelCustomizers.first()) }

        Row {
            // choose a type of appearance
            Text("Choose a type of appearance: ")
            panelCustomizers.forEach {
                it.indicator()
                Spacer(modifier = Modifier.width(8.dp))
            }
        }

        // customize the appearance
        selectedPanelCustomizer.customizer()
    }
}

data class PanelCustomizer(val id: String, val indicator: @Composable () -> Unit, val customizer: @Composable () -> Unit)

