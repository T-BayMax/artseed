package com.bjike.t.baymax.artseed.core.utils

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Environment
import android.telephony.TelephonyManager
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

import java.io.File

object AppUtils {

    /**
     * 获取SD卡路径
     *
     * @return 如果sd卡不存在则返回null
     */
    //判断sd卡是否存在
    //获取跟目录
    val sdPath: File?
        get() {
            var sdDir: File? = null
            val sdCardExist = Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
            if (sdCardExist) {
                sdDir = Environment.getExternalStorageDirectory()
            }
            return sdDir
        }

    /**
     * 获取版本名称
     */
    fun getAppVersionName(context: Context): String? {
        var versionName: String? = ""
        try {
            // ---get the package info---
            val pm = context.packageManager
            val pi = pm.getPackageInfo(context.packageName, 0)
            versionName = pi.versionName
            if (versionName == null || versionName.length <= 0) {
                return ""
            }
        } catch (e: Exception) {
            Log.e("VersionInfo", "Exception", e)
        }

        return versionName
    }

    /**
     * 获取版本号
     */
    fun getAppVersionCode(context: Context): Int {
        var versioncode = -1
        try {
            // ---get the package info---
            val pm = context.packageManager
            val pi = pm.getPackageInfo(context.packageName, 0)
            versioncode = pi.versionCode
        } catch (e: Exception) {
            Log.e("VersionInfo", "Exception", e)
        }

        return versioncode
    }

    fun getIMEI(context: Context): String {
        val tm = context.getSystemService(Context.TELEPHONY_SERVICE) as TelephonyManager
        return tm.deviceId
    }

    /**
     * 显示软键盘
     */
    fun openSoftInput(et: EditText) {
        val inputMethodManager = et.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.showSoftInput(et, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    /**
     * 隐藏软键盘
     */
    fun hideSoftInput(et: EditText) {
        val inputMethodManager = et.context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(et.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
    }

    /**
     * 安装文件
     *
     * @param data
     */
    fun promptInstall(context: Context, data: Uri) {
        val promptInstall = Intent(Intent.ACTION_VIEW)
                .setDataAndType(data, "application/vnd.android.package-archive")
        // FLAG_ACTIVITY_NEW_TASK 可以保证安装成功时可以正常打开 app
        promptInstall.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        context.startActivity(promptInstall)
    }

    fun copy2clipboard(context: Context, text: String) {
        val cm = context.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("clip", text)
        cm.primaryClip = clip
    }

}
