package com.lwons.assist.action

import android.content.Context
import android.media.AudioManager
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf


class VolumeUpActionImpl(private val context: Context): IActionImpl {

    private val st by lazy { mutableIntStateOf(0) }
    private val audioManager by lazy { context.getSystemService(Context.AUDIO_SERVICE) as AudioManager }

    override fun execute(action: Action) {
        when (action) {
            Action.ACTION_VOLUME_UP -> {
                audioManager.adjustVolume(AudioManager.ADJUST_RAISE, AudioManager.FLAG_SHOW_UI)
            }
            Action.ACTION_VOLUME_DOWN -> {
                audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI)
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