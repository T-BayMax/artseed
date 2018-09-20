package com.guangzhou.t.baymax.artseed.core.utils

import android.app.Activity
import android.content.res.Resources
import android.graphics.Color
import android.os.Build
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager


/**
 * author：T-Baymax on 2018/7/5.
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
object StatusBarUtil {

    fun setStatusBar(activity: Activity) {
        setStatusBarColor(activity, 0)
        setStatusBarTranslucent(activity)
        setStatusBarTextColor(activity)
    }

    /**
     * 给activity的状态栏设置颜色
     *
     * @param activity activity
     * @param color    颜色值
     */
    fun setStatusBarColor(activity: Activity, color: Int) {
        // 5.0 以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            activity.window.statusBarColor = color
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)

            val view = View(activity)
            val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity))
            view.layoutParams = params
            view.setBackgroundColor(color)

            val decorView = activity.window.decorView as ViewGroup
            decorView.addView(view)

            val contentView = activity.findViewById<View>(android.R.id.content) as ViewGroup
            contentView.setPadding(0, getStatusBarHeight(activity), 0, 0)
        }
    }

    /**
     * 设置activity全屏，状态栏透明，内容填充到状态栏中
     */
    fun setStatusBarTranslucent(activity: Activity) {
        // 5.0 以上
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val decorView = activity.window.decorView
            decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            activity.window.statusBarColor = Color.TRANSPARENT
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            activity.window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        }
    }

    /**
     * 获取状态栏的高度
     */
    fun getStatusBarHeight(activity: Activity): Int {
        val resources = activity.resources
        val statusBarHeightId = resources.getIdentifier("status_bar_height", "dimen", "android")
        return resources.getDimensionPixelOffset(statusBarHeightId)
    }


    /**
     * 修改状态来字体颜色
     */
    fun setStatusBarTextColor(activity: Activity) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
}
