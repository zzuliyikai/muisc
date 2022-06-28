package com.hanshow.myapplication.utils

import android.annotation.SuppressLint
import android.content.res.Resources
import android.graphics.Rect
import android.view.MotionEvent
import android.view.TouchDelegate
import android.view.View
import android.widget.TextView

/**
 * 扩大点击区域
 * */
fun View.expandTouchArea(size: Int) {
    val parentView = this.parent as View
    parentView.post {
        val rect = Rect()
        this.getHitRect(rect)
        rect.top -= size
        rect.bottom += size
        rect.left -= size
        rect.right += size
        parentView.touchDelegate = TouchDelegate(rect, this)
    }
}

/**
 * dp 转 px
 * */
val Int.dp: Float
    get() {
        return this * Resources.getSystem().displayMetrics.density
    }

/**
 * px 转 dp
 * */
val Int.px: Int
    get() {
        return this.toFloat().px
    }
val Float.px: Int
    get() {
        return (this / Resources.getSystem().displayMetrics.density + 0.5f).toInt()
    }

/**
 * ScrollView嵌套EditText出现的滑动问题解决
 * @param view
 */
@SuppressLint("ClickableViewAccessibility")
fun editTextSlide(view: TextView) {
    view.setOnTouchListener { v, event ->
        v.parent.requestDisallowInterceptTouchEvent(true)
        if (MotionEvent.ACTION_UP == event.action) {
            v.parent.requestDisallowInterceptTouchEvent(false);
        }
        return@setOnTouchListener false
    }
}
