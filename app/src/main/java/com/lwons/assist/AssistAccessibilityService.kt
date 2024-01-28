package com.lwons.assist

import android.accessibilityservice.AccessibilityService
import android.graphics.PixelFormat
import android.graphics.Point
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.view.accessibility.AccessibilityEvent
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.setViewTreeLifecycleOwner
import androidx.savedstate.SavedStateRegistry
import androidx.savedstate.SavedStateRegistryController
import androidx.savedstate.SavedStateRegistryOwner
import androidx.savedstate.setViewTreeSavedStateRegistryOwner
import com.lwons.assist.bubble.Bubble
import com.lwons.assist.pref.GlobalPreferences
import com.lwons.assist.panel.Panel

class AssistAccessibilityService : AccessibilityService(), LifecycleOwner, SavedStateRegistryOwner {

    companion object {
        private const val PREF_KEY_POSITION_X = "position_x"
        private const val PREF_KEY_POSITION_Y = "position_y"

        private const val DEFAULT_POSITION_X = -1
        private const val DEFAULT_POSITION_Y = -1
    }

    private var panelView: View? = null

    private val lifecycleRegistry = LifecycleRegistry(this)
    private var savedStateRegistryController: SavedStateRegistryController = SavedStateRegistryController.create(this)

    private var position: Point = Point(DEFAULT_POSITION_X, DEFAULT_POSITION_Y)

    override fun onServiceConnected() {
        Log.i("AssistAccessibilityService", "onServiceConnected")
        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager

        val bubbleComposeView = ComposeView(context = this)
        bubbleComposeView.setContent {
            Bubble(onRequestTranslate = { dx, dy ->
                Log.i("AssistAccessibilityService", "onRequestTranslate: $dx, $dy")
                position.x += dx
                position.y += dy
                windowManager.updateViewLayout(bubbleComposeView, getLayoutParams())
            }, onRequestFinished = {
                GlobalPreferences.saveInt(PREF_KEY_POSITION_X, position.x)
                GlobalPreferences.saveInt(PREF_KEY_POSITION_Y, position.y)
            }, onClick = {
                if (!panelVisible()) {
                    showPanel()
                }
                Log.i("AssistAccessibilityService", "onClick")
            }, modifier = Modifier.width(64.dp).height(64.dp))
        }

        windowManager.addView(bubbleComposeView, getLayoutParams())
        bubbleComposeView.setViewTreeLifecycleOwner(this)
        bubbleComposeView.setViewTreeSavedStateRegistryOwner(this)
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

    private fun togglePanel() {
        if (panelVisible()) {
            hidePanel()
        } else {
            showPanel()
        }
    }

    private fun showPanel() {
        if (panelVisible()) {
            return
        }
        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        val panelComposeView = ComposeView(context = this)
        panelComposeView.setContent {
            Panel(dismissListener = {
                hidePanel()
            })
        }
        windowManager.addView(panelComposeView, WindowManager.LayoutParams().apply {
            width = WindowManager.LayoutParams.MATCH_PARENT
            height = WindowManager.LayoutParams.MATCH_PARENT
            type = WindowManager.LayoutParams.TYPE_ACCESSIBILITY_OVERLAY
            gravity = Gravity.CENTER
            format = PixelFormat.TRANSPARENT
            flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
        })
        panelComposeView.setViewTreeLifecycleOwner(this)
        panelComposeView.setViewTreeSavedStateRegistryOwner(this)
        panelView = panelComposeView
    }

    private fun hidePanel() {
        if (!panelVisible()) {
            return
        }
        val windowManager = getSystemService(WINDOW_SERVICE) as WindowManager
        windowManager.removeView(panelView)
        panelView = null
    }

    private fun panelVisible(): Boolean {
        return panelView != null
    }

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