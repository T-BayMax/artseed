package com.guangzhou.t.baymax.artseed.core.utils

import android.app.Activity
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo

class NetUtils private constructor() {

    init {
        /* cannot be instantiated */
        throw UnsupportedOperationException("cannot be instantiated")
    }

    companion object {

        /**
         * 判断网络是否连接
         */
        fun isConnected(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    ?: return false

            val info = cm.activeNetworkInfo
            if (null != info && info.isConnected) {
                if (info.state == NetworkInfo.State.CONNECTED) {
                    return true
                }
            }
            return false
        }

        /**
         * 判断是否是wifi连接
         */
        fun isWifi(context: Context): Boolean {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
                    ?: return false

            val info = cm.activeNetworkInfo
            if (null != info) {
                if (info.type == ConnectivityManager.TYPE_WIFI) {
                    return true
                }
            }
            return false

        }

        /**
         * 打开网络设置界面
         */
        fun openSetting(activity: Activity, requestCode: Int) {
            val intent = Intent("/")
            val cm = ComponentName("com.android.settings",
                    "com.android.settings.WirelessSettings")
            intent.component = cm
            intent.action = Intent.ACTION_VIEW
            activity.startActivityForResult(intent, requestCode)
        }
    }

}
