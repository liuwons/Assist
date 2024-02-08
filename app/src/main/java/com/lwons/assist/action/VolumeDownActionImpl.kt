package com.lwons.assist.action

import android.content.Context
import android.media.AudioManager
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf


class VolumeDownActionImpl(private val context: Context): IActionImpl {

    private val st by lazy { mutableIntStateOf(0) }
    private val audioManager by lazy { context.getSystemService(Context.AUDIO_SERVICE) as AudioManager }

    override fun execute(action: Action) {
        audioManager.adjustVolume(AudioManager.ADJUST_LOWER, AudioManager.FLAG_SHOW_UI)
    }

    override fun state(): State<Int> {
        return st
    }
}