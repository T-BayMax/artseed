package com.bjike.t.baymax.artseed.core.base

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Build
import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.content.ContextCompat
import android.support.v7.app.AppCompatActivity
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout

import com.guangzhou.t.baymax.artseed.R
import com.guangzhou.t.baymax.artseed.core.AppManager
import com.guangzhou.t.baymax.artseed.core.utils.ClassUtil
import com.guangzhou.t.baymax.artseed.core.utils.TitleBuilder


/**
 * 同一管理activity
 * author：T-Baymax on 2018/09/07 09:46
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
abstract class CoreBaseMVPActivity<P : CoreBasePresenter<*, *>, E : CoreBaseModel> : AppCompatActivity() {

    private var TAG: String? = null

    var mPresenter: P? = null
    var mModel: E? = null
    private var mContext: Context? = null

    private var ivShadow: ImageView? = null
    val isOpen = true

    private val container: View
        get() {
            val container = RelativeLayout(this)
            ivShadow = ImageView(this)
            ivShadow!!.setBackgroundColor(ContextCompat.getColor(this, R.color.design_default_color_primary))
            val params = RelativeLayout.LayoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.MATCH_PARENT)
            container.addView(ivShadow, params)
            return container
        }

    @LayoutRes
    abstract fun getLayoutId(): Int

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        TAG = javaClass.simpleName

        this.setContentView(this.getLayoutId())
        mContext = this
        mPresenter = ClassUtil.getT<P>(this, 0)
        mModel = ClassUtil.getT<E>(this, 1)
        if (this is CoreBaseView) mPresenter!!.attachVM(this, mModel!!)
        this.initView(savedInstanceState)

        AppManager.appManager.addActivity(this)
    }

    /**
     * 结束是注销Presenter
     */
    override fun onDestroy() {
        super.onDestroy()
        AppManager.appManager.finishActivity(this)
        if (mPresenter != null) mPresenter!!.detachVM()
    }

    override fun onResume() {
        super.onResume()
    }

    /**
     * 重载当前Activity
     */
    fun reload() {
        val intent = intent
        overridePendingTransition(0, 0)
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
        finish()
        overridePendingTransition(0, 0)
        startActivity(intent)
    }

    override fun setContentView(layoutResID: Int) {
        if (isOpen) {
            super.setContentView(layoutResID)
        } else {
            super.setContentView(container)
            val view = LayoutInflater.from(this).inflate(layoutResID, null)
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
        }
    }

    abstract fun initView(savedInstanceState: Bundle?)

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
                    finish()
                })
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
