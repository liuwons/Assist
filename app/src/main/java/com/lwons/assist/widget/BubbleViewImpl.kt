package com.lwons.assist.widget

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Point
import android.os.Handler
import android.os.Looper
import android.view.MotionEvent
import android.view.View
import android.view.ViewConfiguration
import com.lwons.assist.R
import kotlin.math.abs
import kotlin.math.max

class BubbleViewImpl(val onRequestTranslate: (Int, Int) -> Unit,
                     val onRequestFinished: () -> Unit,
                     val onClick: () -> Unit) {

    companion object {
        private const val ALPHA_ACTIVE = 1.0f
        private const val ALPHA_INACTIVE = 0.3f
        private const val INACTIVE_TIMEOUT = 3000L
    }

    private val lastPoint = Point()
    private val firstPoint = Point()
    private var touchMaxDistance = 0

    private val mainHandler = Handler(Looper.getMainLooper())

    @SuppressLint("ClickableViewAccessibility")
    fun createView(context: Context): View {
        return View(context).apply {
            background = context.resources.getDrawable(R.drawable.ic_bubble)
            alpha = ALPHA_INACTIVE
            setOnTouchListener { _, event ->
                event?.let {
                    val eventX = event.rawX.toInt()
                    val eventY = event.rawY.toInt()
                    when (event.action) {
                        MotionEvent.ACTION_DOWN -> {
                            mainHandler.removeCallbacksAndMessages(null)
                            alpha = ALPHA_ACTIVE

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
                            mainHandler.postDelayed({ alpha = ALPHA_INACTIVE }, INACTIVE_TIMEOUT)

                            onRequestFinished()
                            if (touchMaxDistance < ViewConfiguration.get(context).scaledTouchSlop) {
                                onClick()
                            }
                        }

                        else -> {}
                    }
                }
                true
            }
        }
    }

}