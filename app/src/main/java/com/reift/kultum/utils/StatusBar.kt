package com.reift.kultum.utils

import android.app.Activity
import android.view.View

object StatusBar {
    fun setLightIcon(activity: Activity) {
        activity.window.decorView.systemUiVisibility = 0
    }
    fun setDarkIcon(activity: Activity) {
        activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
}