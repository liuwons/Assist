package com.lwons.assist.action

import android.content.Context
import android.os.PowerManager
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import com.lwons.assist.log.Logger


class WakeLockActionImpl(private val context: Context): IActionImpl {
    companion object {
        private const val TAG = "WakeLockActionImpl"
    }

    private val st by lazy { mutableIntStateOf(0) }
    private val wakeLock = (context.getSystemService(Context.POWER_SERVICE) as PowerManager).newWakeLock(
        PowerManager.SCREEN_BRIGHT_WAKE_LOCK or PowerManager.ON_AFTER_RELEASE, "assist:wakeLock"
    )

    override fun execute(action: Action) {
        Logger.i(TAG, "execute: $action")
        when (action) {
            Action.ACTION_KEEP_SCREEN_ON -> {
                if (st.intValue == 0) {
                    Logger.i(TAG, "acquire wake lock")
                    wakeLock.acquire()
                    st.intValue = 1
                } else {
                    Logger.i(TAG, "release wake lock")
                    wakeLock.release()
                    st.intValue = 0
                }
            }
            else -> {
                // do nothing
            }
        }
    }

    override fun state(): State<Int> {
        return st
    }
}