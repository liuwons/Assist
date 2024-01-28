package com.lwons.assist.bubble

import android.graphics.Point
import android.view.MotionEvent
import android.view.ViewConfiguration
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import com.lwons.assist.R
import kotlin.math.abs
import kotlin.math.max


private val lastPoint = Point()
private val firstPoint = Point()
private var touchMaxDistance = 0

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun Bubble(onRequestTranslate: (Int, Int) -> Unit,
           onRequestFinished: () -> Unit,
           onClick: () -> Unit,
           modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Box(modifier = modifier.clickable { onClick() }.pointerInteropFilter { event ->
        val eventX = event.rawX.toInt()
        val eventY = event.rawY.toInt()
        when(event.action) {
            MotionEvent.ACTION_DOWN -> {
                firstPoint.set(eventX, eventY)
                lastPoint.set(eventX, eventY)
                touchMaxDistance = 0
            }
            MotionEvent.ACTION_MOVE -> {
                onRequestTranslate(eventX - lastPoint.x, eventY - lastPoint.y)
                lastPoint.set(eventX, eventY)
                val dist = max(abs(eventX - firstPoint.x), abs(eventY - firstPoint.y))
                touchMaxDistance = max(dist, touchMaxDistance)
            }
            MotionEvent.ACTION_UP -> {
                onRequestFinished()
                if (touchMaxDistance < ViewConfiguration.get(context).scaledTouchSlop) {
                    onClick()
                }
            }
            else -> {}
        }
        true
    }) {
        Image(modifier = modifier, painter = painterResource(id = R.drawable.icon_bubble), contentDescription = "Assist")
    }
}