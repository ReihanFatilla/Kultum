package com.reift.kultum.customview

import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.viewpager.widget.ViewPager

class VerticalViewPager(context: Context, attrs: AttributeSet?) :
    ViewPager(context, attrs) {
    constructor(context: Context) : this(context, null) {}

    override fun canScrollHorizontally(direction: Int): Boolean {
        return false
    }

    override fun canScrollVertically(direction: Int): Boolean {
        return super.canScrollHorizontally(direction)
    }

    private fun init() {
        setPageTransformer(true, VerticalPageTransformer())
        overScrollMode = View.OVER_SCROLL_NEVER
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        val toIntercept = super.onInterceptTouchEvent(flipXY(ev))
        flipXY(ev)
        return toIntercept
    }

    override fun onTouchEvent(ev: MotionEvent): Boolean {
        val toHandle = super.onTouchEvent(flipXY(ev))
        flipXY(ev)
        return toHandle
    }

    private fun flipXY(ev: MotionEvent): MotionEvent {
        val width = width.toFloat()
        val height = height.toFloat()
        val x = ev.y / height * width
        val y = ev.x / width * height
        ev.setLocation(x, y)
        return ev
    }

    private class VerticalPageTransformer : PageTransformer {
        override fun transformPage(view: View, position: Float) {
            val pageWidth: Int = view.width
            val pageHeight: Int = view.height
            if (position < -1) {
                view.alpha = 0F
            } else if (position <= 1) {
                view.alpha = 1f
                view.translationX = pageWidth * -position
                val yPosition = position * pageHeight
                view.translationY = yPosition
            } else {
                view.alpha = 0f
            }
        }
    }

    init {
        init()
    }
}