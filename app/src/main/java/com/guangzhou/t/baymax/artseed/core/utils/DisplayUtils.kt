package com.bjike.t.baymax.artseed.core.utils

import android.app.Activity
import android.content.Context
import android.util.DisplayMetrics

object DisplayUtils {
    /**
     * 将px值转换为dp值
     */
    fun px2dp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将dp值转换为px值
     */
    fun dp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.density
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 将px值转换为sp值
     */
    fun px2sp(context: Context, pxValue: Float): Int {
        val scale = context.resources.displayMetrics.scaledDensity
        return (pxValue / scale + 0.5f).toInt()
    }

    /**
     * 将sp值转换为px值
     */
    fun sp2px(context: Context, dpValue: Float): Int {
        val scale = context.resources.displayMetrics.scaledDensity
        return (dpValue * scale + 0.5f).toInt()
    }

    /**
     * 获取屏幕宽度
     */
    fun getScreenWidthPixels(context: Activity): Int {
        val metric = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(metric)
        return metric.widthPixels
    }

    /**
     * 获取屏幕高度
     */
    fun getScreenHeightPixels(context: Activity): Int {
        val metric = DisplayMetrics()
        context.windowManager.defaultDisplay.getMetrics(metric)
        return metric.heightPixels
    }
    fun Context.getVersionCode(): String {

        var version = ""
        try {
            // 获取packagemanager的实例
            val packageManager = getPackageManager()
            // getPackageName()是你当前类的包名，0代表是获取版本信息
            val packInfo = packageManager.getPackageInfo(getPackageName(), 0)
            version = packInfo.versionName
            return version
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return version
    }
}
