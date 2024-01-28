package com.lwons.assist.touch

import android.graphics.Point
import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.res.painterResource
import com.lwons.assist.R


private val lastPoint = Point()

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Bubble(onRequestTranslate: (Int, Int) -> Unit, onRequestFinished: () -> Unit, modifier: Modifier = Modifier) {
    Box(modifier = modifier.pointerInteropFilter { event ->
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                lastPoint.x = event.rawX.toInt()
                lastPoint.y = event.rawY.toInt()
            }
            MotionEvent.ACTION_MOVE -> {
                val dx = event.rawX.toInt() - lastPoint.x
                val dy = event.rawY.toInt() - lastPoint.y
                lastPoint.x = event.rawX.toInt()
                lastPoint.y = event.rawY.toInt()
                onRequestTranslate(dx, dy)
            }
            MotionEvent.ACTION_UP -> {
                onRequestFinished()
            }
            else -> {}
        }
        true
    }) {
        Image(modifier = modifier, painter = painterResource(id = R.drawable.icon_bubble), contentDescription = "Assist")
    }
}