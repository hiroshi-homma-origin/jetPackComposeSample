package com.kotlin.pagecurl.viewExt

import android.annotation.SuppressLint
import android.content.Context
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View

open class OnSwipeTouchListener constructor(
    context: Context
) : View.OnTouchListener {

    private val gestureDetector = GestureDetector(context, GestureListener())

    fun onTouch(event: MotionEvent): Boolean {
        return gestureDetector.onTouchEvent(event)
    }

    private inner class GestureListener : GestureDetector.SimpleOnGestureListener() {

        override fun onDown(e: MotionEvent): Boolean {
            return true
        }

        override fun onSingleTapConfirmed(e: MotionEvent): Boolean {
            onTouch(e)
            return true
        }
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        event.setLocation(event.x, 0f)
        onMotionEvent(event)
        return gestureDetector.onTouchEvent(event)
    }

    open fun onMotionEvent(event: MotionEvent) {}
}
