package com.bjike.t.baymax.artseed.core.utils

import android.content.Context
import android.content.SharedPreferences

/**
 * author：T-Baymax on 2018/3/10 10:04
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
class PreferenceService(private val context: Context) {
    var sharedPreferences: SharedPreferences
    var editor: SharedPreferences.Editor

    init {
        sharedPreferences = context.getSharedPreferences("artseed",0)
        editor = sharedPreferences.edit()
    }

    /**
     * 保存设置
     * @param key
     * @param value
     */
    fun addSetting(key: String, value: String) {

        editor.putString(key, value)

        editor.commit()
    }

    /**
     * 获取设置
     * @return
     */
    fun getSetting(key: String): String {

        // 使用getString方法获得value，注意第2个参数是value的默认值
        return sharedPreferences.getString(key, "")
    }

    /**
     * 清掉某个设置
     * @param key
     */
    fun clearEditor(key: String) {

        /**开始清除SharedPreferences中保存的内容 */
        editor.remove(key)
        editor.commit()
    }

    /**
     * 清除所有设置
     */
    fun clearData() {

        editor.clear().commit()
    }
}
