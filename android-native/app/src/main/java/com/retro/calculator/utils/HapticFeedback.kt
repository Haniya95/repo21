package com.retro.calculator.utils

import android.content.Context
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import android.os.VibratorManager

class HapticFeedback(private val context: Context) {
    
    private val vibrator: Vibrator by lazy {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {
            val vibratorManager = context.getSystemService(Context.VIBRATOR_MANAGER_SERVICE) as VibratorManager
            vibratorManager.defaultVibrator
        } else {
            @Suppress("DEPRECATION")
            context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        }
    }
    
    fun performHapticFeedback(type: HapticType = HapticType.LIGHT) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val effect = when (type) {
                HapticType.LIGHT -> VibrationEffect.createOneShot(50, VibrationEffect.DEFAULT_AMPLITUDE)
                HapticType.MEDIUM -> VibrationEffect.createOneShot(100, VibrationEffect.DEFAULT_AMPLITUDE)
                HapticType.HEAVY -> VibrationEffect.createOneShot(200, VibrationEffect.DEFAULT_AMPLITUDE)
                HapticType.SUCCESS -> VibrationEffect.createWaveform(longArrayOf(0, 100, 50, 100), -1)
                HapticType.ERROR -> VibrationEffect.createWaveform(longArrayOf(0, 200, 100, 200, 100, 200), -1)
            }
            vibrator.vibrate(effect)
        } else {
            @Suppress("DEPRECATION")
            when (type) {
                HapticType.LIGHT -> vibrator.vibrate(50)
                HapticType.MEDIUM -> vibrator.vibrate(100)
                HapticType.HEAVY -> vibrator.vibrate(200)
                HapticType.SUCCESS -> vibrator.vibrate(longArrayOf(0, 100, 50, 100), -1)
                HapticType.ERROR -> vibrator.vibrate(longArrayOf(0, 200, 100, 200, 100, 200), -1)
            }
        }
    }
}

enum class HapticType {
    LIGHT,
    MEDIUM,
    HEAVY,
    SUCCESS,
    ERROR
}