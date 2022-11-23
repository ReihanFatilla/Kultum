package com.reift.kultum.utils

import android.app.Activity
import android.os.Build
import android.view.Window
import android.view.WindowManager

object Transparent {

    fun statusbar(activity: Activity) {
        val w: Window = activity.window
        w.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
    }

}