package com.lwons.assist.action

enum class Action {
    ACTION_NONE,
    ACTION_HOME,
    ACTION_SETTINGS,
    ACTION_QUICK_SETTINGS,
    ACTION_SCREENSHOT,
    ACTION_BACK,
    ACTION_POWER,
    ACTION_SILENT,
    ACTION_NOTIFICATION,
    ACTION_LOCK,
    ACTION_VOLUME_UP,
    ACTION_VOLUME_DOWN,
    ACTION_ROTATION,
    ACTION_BLUETOOTH,
    ACTION_WIFI,
    ACTION_FLASHLIGHT,
    ACTION_CAMERA,
    ACTION_CALCULATOR,
    ACTION_DARK_MODE,
    ACTION_BRIGHTNESS,
    ACTION_RECORD,
    ACTION_RECENT,
    ACTION_KEEP_SCREEN_ON,
    ACTION_DEVELOPER_OPTIONS,
    ACTION_DEV_LAYOUT_BOUNDS,
    ACTION_DEV_GPU_OVERDRAW,
    ACTION_DEV_SHOW_TAPS,
    ACTION_DEV_STRICT_MODE,
    ACTION_DEV_POINTER_LOCATION,
    ACTION_DEV_SHOW_CPU,
    ACTION_DEV_FORCE_GPU_RENDERING,
    ACTION_DEV_SHOW_GPU_VIEW_UPDATES,
    ACTION_DEV_WIRELESS_DEBUGGING,
}

data class AssistAction(val action: Action, val name: String, val icon: (Int) -> Int)