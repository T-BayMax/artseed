package com.guangzhou.t.baymax.artseed.core.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.RelativeLayout
import androidx.core.content.ContextCompat

import com.guangzhou.t.baymax.artseed.R
import com.guangzhou.t.baymax.artseed.core.AppManager
import com.guangzhou.t.baymax.artseed.core.utils.ClassUtil


/**
 * 同一管理activity
 * author：T-Baymax on 2018/09/07 09:46
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
abstract class CoreBaseMVPActivity<P : CoreBasePresenter<*, *>, E : CoreBaseModel> : CoreBaseActivity() {

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


    override fun init(savedInstanceState: Bundle?) {
        TAG = javaClass.simpleName

        //  super.setContentView(this.getLayoutId())
        mContext = this
        mPresenter = ClassUtil.getT<P>(this, 0)
        mModel = ClassUtil.getT<E>(this, 1)
        if (this is CoreBaseView) mPresenter!!.attachVM(this, mModel!!)

     //   AppManager.appManager.addActivity(this)
    }
/*

    */
/**
     * 结束是注销Presenter
     *//*

    override fun onDestroy() {
        super.onDestroy()
        AppManager.appManager.finishActivity(this)
        if (mPresenter != null) mPresenter!!.detachVM()
    }

    override fun onResume() {
        super.onResume()
    }
*/

    override fun setContentView(layoutResID: Int) {
        if (isOpen) {
            super.setContentView(layoutResID)
        } else {
            super.setContentView(container)
            val view = LayoutInflater.from(this).inflate(layoutResID, null)
            view.setBackgroundColor(ContextCompat.getColor(this, R.color.colorPrimary))
        }
    }
}
