package com.reift.kultum.utils

import android.view.View
import android.view.animation.BounceInterpolator
import android.view.animation.Interpolator

object Animator {
    fun bouncyAnimation(view: View) {
        val interpolator: Interpolator = BounceInterpolator()

        view.animate().scaleX(1.4f).scaleY(1.4f).setDuration(100).setInterpolator(interpolator).withEndAction{
            view.animate().scaleX(0.8f).scaleY(0.8f).setDuration(100).setInterpolator(interpolator).withEndAction {
                view.animate().scaleX(1.25f).scaleY(1.25f).setDuration(200).setInterpolator(interpolator).withEndAction {
                    view.animate().scaleX(1f).scaleY(1f).setInterpolator(interpolator).duration = 200
                }
            }
        }
    }

    fun jumpAnimation(view: View) {
        view.animate().translationY(-80f).scaleX(1.4f).scaleY(1.4f).setDuration(200)
            .withEndAction {
                view.animate().translationY(40f).scaleX(0.7f).scaleY(0.7f).setDuration(200).withEndAction {
                    view.animate().translationY(0f).scaleX(1f).scaleY(1f).setDuration(200)
                }
            }
    }
}