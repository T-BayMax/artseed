package com.bjike.t.baymax.artseed.core.utils

import android.app.Dialog
import android.app.ProgressDialog
import android.content.Context
import android.content.DialogInterface
import android.support.v7.app.AlertDialog
import android.view.Gravity
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView

import com.bjike.t.baymax.artseed.R

/**
 * 对话框工具类, 提供常用对话框显示, 使用support.v7包内的AlertDialog样式
 */
object DialogUtils {

    /**
     * 加载时对话框
     */
    fun showLoadingDialog(context: Context, msg: String): Dialog {
        val inflater = LayoutInflater.from(context)
        val v = inflater.inflate(R.layout.dialog_loading, null)// 得到加载view
        val layout = v.findViewById<LinearLayout>(R.id.dialog_loading_view)// 加载布局
        val tipTextView = v.findViewById<TextView>(R.id.tipTextView)// 提示文字
        tipTextView.text = msg// 设置加载信息

        val loadingDialog = Dialog(context, R.style.MyDialogStyle)// 创建自定义样式dialog
        loadingDialog.setCancelable(true) // 是否可以按“返回键”消失
        loadingDialog.setCanceledOnTouchOutside(false) // 点击加载框以外的区域
        loadingDialog.setContentView(layout, LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT))// 设置布局
        /**
         * 将显示Dialog的方法封装在这里面
         */
        val window = loadingDialog.window
        val lp = window.attributes
        lp.width = WindowManager.LayoutParams.MATCH_PARENT
        lp.height = WindowManager.LayoutParams.WRAP_CONTENT
        window.setGravity(Gravity.CENTER)
        window.attributes = lp
        window.setWindowAnimations(R.style.PopWindowAnimStyle)
        loadingDialog.show()

        return loadingDialog
    }

    fun showCommonDialog(context: Context, message: String,
                         listener: DialogInterface.OnClickListener): Dialog {
        return AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.dialog_positive), listener)
                .setNegativeButton(context.getString(R.string.dialog_negative), null)
                .show()
    }

    fun showConfirmDialog(context: Context, message: String,
                          listener: DialogInterface.OnClickListener): Dialog {
        return AlertDialog.Builder(context)
                .setMessage(message)
                .setPositiveButton(context.getString(R.string.dialog_positive), listener)
                .show()
    }

}
