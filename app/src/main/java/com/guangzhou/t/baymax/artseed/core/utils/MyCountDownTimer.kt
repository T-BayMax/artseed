package com.bjike.t.baymax.artseed.core.utils

import android.os.CountDownTimer
import android.widget.TextView

/**
 * 倒计时
 * author：T-Baymax on 2018/3/10 10:04
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */

class MyCountDownTimer : CountDownTimer {
    private var view: TextView? = null

    constructor(millisInFuture: Long, countDownInterval: Long) : super(millisInFuture, countDownInterval) {}
    constructor(millisInFuture: Long, countDownInterval: Long, view: TextView) : super(millisInFuture, countDownInterval) {
        this.view = view
    }

    //计时过程
    override fun onTick(l: Long) {
        //防止计时过程中重复点击
        if (view != null) {
            view!!.isClickable = false
            view!!.text = (l / 1000).toString() + "s"
        }

    }

    //计时完毕的方法
    override fun onFinish() {
        //重新给Button设置文字
        if (view != null) {
            view!!.text = "重新获取验证码"
            //设置可点击
            view!!.isClickable = true
        }
    }

}
