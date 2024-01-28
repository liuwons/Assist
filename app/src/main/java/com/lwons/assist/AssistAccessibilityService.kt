package com.lwons.assist

import android.accessibilityservice.AccessibilityService
import android.graphics.PixelFormat
import android.graphics.Point
import android.util.Log
import android.view.Gravity
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import androidx.compose.ui.platform.ComposeView
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.lwons.assist.pref.GlobalPreferences
import com.lwons.assist.touch.Entry

class AssistAccessibilityService : AccessibilityService(), LifecycleOwner, SavedStateRegistryOwner {

    companion object {
        private const val PREF_KEY_POSITION_X = "position_x"
        private const val PREF_KEY_POSITION_Y = "position_y"

        private const val DEFAULT_POSITION_X = -1
        private const val DEFAULT_POSITION_Y = -1
    }

    private val lifecycleRegistry = LifecycleRegistry(this)
    private var savedStateRegistryController: SavedStateRegistryController = SavedStateRegistryController.create(this)

    private var position: Point = Point(DEFAULT_POSITION_X, DEFAULT_POSITION_Y)

    override fun onServiceConnected() {
        Log.i("AssistAccessibilityService", "onServiceConnected")
        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        val composeView = ComposeView(context = this)
        composeView.setContent {
            Entry(onRequestTranslate = { dx, dy ->
                Log.i("AssistAccessibilityService", "onRequestTranslate: $dx, $dy")
                position.x += dx
                position.y += dy
                windowManager.updateViewLayout(composeView, getLayoutParams())
            }, onRequestFinished = {
                GlobalPreferences.saveInt(PREF_KEY_POSITION_X, position.x)
                GlobalPreferences.saveInt(PREF_KEY_POSITION_Y, position.y)
            })
        }

        windowManager.addView(composeView, getLayoutParams())
        composeView.setViewTreeLifecycleOwner(this)
        composeView.setViewTreeSavedStateRegistryOwner(this)
    }

    override fun onAccessibilityEvent(event: AccessibilityEvent?) {
        Log.i("AssistAccessibilityService", "onAccessibilityEvent")
    }

    override fun onInterrupt() {
        Log.i("AssistAccessibilityService", "onInterrupt")
    }

    override fun onCreate() {
        super.onCreate()
        savedStateRegistryController.performRestore(null)
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_CREATE)
        Log.i("AssistAccessibilityService", "onCreate")

        position = Point(GlobalPreferences.loadInt(PREF_KEY_POSITION_X, DEFAULT_POSITION_X),
            GlobalPreferences.loadInt(PREF_KEY_POSITION_Y, DEFAULT_POSITION_Y))

        if (position.x == DEFAULT_POSITION_X || position.y == DEFAULT_POSITION_Y) {
            val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
            val display = windowManager.defaultDisplay
            val size = Point()
            display.getSize(size)
            position = Point(size.x / 2, size.y / 2)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        lifecycleRegistry.handleLifecycleEvent(Lifecycle.Event.ON_DESTROY)
        Log.i("AssistAccessibilityService", "onDestroy")
    }

    override val lifecycle: Lifecycle
        get() = lifecycleRegistry
    override val savedStateRegistry: SavedStateRegistry
        get() = savedStateRegistryController.savedStateRegistry

    private fun getLayoutParams(): WindowManager.LayoutParams {
        return WindowManager.LayoutParams().apply {
            x = position.x
            y = position.y
            width = WindowManager.LayoutParams.WRAP_CONTENT
            height = WindowManager.LayoutParams.WRAP_CONTENT
            type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY
            gravity = Gravity.TOP or Gravity.START
            format = PixelFormat.TRANSPARENT
            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        }
    }

}