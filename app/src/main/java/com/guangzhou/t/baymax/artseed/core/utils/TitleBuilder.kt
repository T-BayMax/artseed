package com.guangzhou.t.baymax.artseed.core.utils

import android.app.Activity
import android.content.Context
import android.text.TextUtils
import android.view.View
import android.view.View.OnClickListener
import android.widget.ImageView
import android.widget.TextView

import com.guangzhou.baymax.artseed.R


/**
 * 标题栏构造器,使用方法 new TitleBuilder().setMethod().setMethod()......
 *
 *
 * 统一格式为标题文字,左右各自是文字/图片按钮
 * 按钮都默认不显示,只有在你调用setLeftText时才会显示左侧按钮文字,图片同理
 * 图片或文字的点击事件都用Left/RightOnClickListener
 */
class TitleBuilder {

    var rootView: View? = null
        private set
    var tvTitle: TextView? = null
        private set
    var ivLeft: ImageView? = null
        private set
    var ivRight: ImageView? = null
        private set
    var tvLeft: TextView? = null
        private set
    var tvRight: TextView? = null
        private set

    /**
     * Activity中使用这个构造方法
     */
    constructor(context: Activity) {
        rootView = context.findViewById(R.id.rl_titlebar)
        if (rootView == null) {
            return
        }
        tvTitle = rootView!!.findViewById<View>(R.id.titlebar_tv) as TextView
        ivLeft = rootView!!.findViewById<View>(R.id.titlebar_iv_left) as ImageView
        ivRight = rootView!!.findViewById<View>(R.id.titlebar_iv_right) as ImageView
        tvLeft = rootView!!.findViewById<View>(R.id.titlebar_tv_left) as TextView
        tvRight = rootView!!.findViewById<View>(R.id.titlebar_tv_right) as TextView
    }

    /**
     * Fragment中使用这个构造方法
     */
    constructor(context: View) {
        rootView = context.findViewById(R.id.rl_titlebar)
        if (rootView == null) {
            return
        }
        tvTitle = rootView!!.findViewById<View>(R.id.titlebar_tv) as TextView
        ivLeft = rootView!!.findViewById<View>(R.id.titlebar_iv_left) as ImageView
        ivRight = rootView!!.findViewById<View>(R.id.titlebar_iv_right) as ImageView
        tvLeft = rootView!!.findViewById<View>(R.id.titlebar_tv_left) as TextView
        tvRight = rootView!!.findViewById<View>(R.id.titlebar_tv_right) as TextView
    }

    // title
    fun setTitleBgRes(resid: Int): TitleBuilder {
        rootView!!.setBackgroundResource(resid)
        return this
    }

    fun setTitleText(text: String): TitleBuilder {
        tvTitle!!.visibility = if (TextUtils.isEmpty(text))
            View.GONE
        else
            View.VISIBLE
        tvTitle!!.text = text
        return this
    }

    // left
    fun setLeftImage(resId: Int): TitleBuilder {
        ivLeft!!.visibility = if (resId > 0) View.VISIBLE else View.GONE
        ivLeft!!.setImageResource(resId)
        return this
    }

    fun setLeftText(text: String): TitleBuilder {
        tvLeft!!.visibility = if (TextUtils.isEmpty(text)) View.GONE else View.VISIBLE
        tvLeft!!.text = text
        return this
    }

    fun setLeftOnClickListener(listener: OnClickListener): TitleBuilder {
        if (ivLeft!!.visibility == View.VISIBLE) {
            ivLeft!!.setOnClickListener(listener)
        } else if (tvLeft!!.visibility == View.VISIBLE) {
            tvLeft!!.setOnClickListener(listener)
        }
        return this
    }

    // right
    fun setRightImage(resId: Int): TitleBuilder {
        ivRight!!.visibility = if (resId > 0) View.VISIBLE else View.GONE
        ivRight!!.setImageResource(resId)
        return this
    }

    fun setRightText(text: String): TitleBuilder {
        tvRight!!.visibility = if (TextUtils.isEmpty(text))
            View.GONE
        else
            View.VISIBLE
        tvRight!!.text = text
        return this
    }

    fun setRightTextColor(context: Context, resId: Int): TitleBuilder {
        tvRight!!.setTextColor(context.resources.getColor(resId))
        return this
    }

    fun setTitleTextColor(context: Context, resId: Int): TitleBuilder {
        tvTitle!!.setTextColor(context.resources.getColor(resId))
        return this
    }


    fun setRightOnClickListener(listener: OnClickListener): TitleBuilder {
        if (ivRight!!.visibility == View.VISIBLE) {
            ivRight!!.setOnClickListener(listener)
        } else if (tvRight!!.visibility == View.VISIBLE) {
            tvRight!!.setOnClickListener(listener)
        }
        return this
    }

    fun build(): View? {
        return rootView
    }

}
