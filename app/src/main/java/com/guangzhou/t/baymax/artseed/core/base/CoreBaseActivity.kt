package com.bjike.t.baymax.artseed.core.base

import android.app.Activity
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.bjike.t.baymax.artseed.R
import com.bjike.t.baymax.artseed.core.AppManager
import com.bjike.t.baymax.artseed.core.utils.TitleBuilder

/**
 * author：T-Baymax on 2018/12/22.
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
abstract class CoreBaseActivity : AppCompatActivity() {

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        this.setContentView(this.getLayoutId())
        AppManager.appManager.addActivity(this)
        this.init(savedInstanceState)
        this.initView(savedInstanceState)

       // window.setBackgroundDrawable(null)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        //  fullScreen()
        /*   if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
               window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
           }*/
        // notSetStatusBarColor()

    }

    open fun init(savedInstanceState: Bundle?) {}
    abstract fun initView(savedInstanceState: Bundle?)

     override fun onDestroy() {
        super.onDestroy()
        AppManager.appManager.finishActivity(this)
    }

    /**
     * 左侧有返回键的标题栏
     *
     * 如果在此基础上还要加其他内容,比如右侧有文字按钮,可以获取该方法返回值继续设置其他内容
     *
     * @param title 标题
     */
    protected fun initBackTitle(title: String): TitleBuilder {
        return TitleBuilder(this)
                .setTitleText(title)
                .setLeftImage(R.mipmap.ic_back)
                .setLeftOnClickListener(View.OnClickListener {
                    AppManager.appManager.finishActivity()
                })
    }

    /**
     * 重载当前Activity
     */
    fun reload() {
        val intent = intent
        overridePendingTransition(0, 0)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)

        AppManager.appManager.finishActivity()
        overridePendingTransition(0, 0)
        startActivity(intent)
    }

    /**
     * 跳转页面,带options参数
     *
     * @param tarActivity 目标页面
     */
    fun startActivity(tarActivity: Class<out Activity>, options: Bundle) {
        val intent = Intent(this, tarActivity)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            startActivity(intent, options)
        } else {
            startActivity(intent)
        }
    }

    /**
     * 跳转页面,无extra简易型
     *
     * @param tarActivity 目标页面
     */
    fun startActivity(tarActivity: Class<out Activity>) {
        val intent = Intent(this, tarActivity)
        startActivity(intent)
    }
}