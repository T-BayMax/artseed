package com.guangzhou.t.baymax.artseed.core.base

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.guangzhou.t.baymax.artseed.core.utils.ClassUtil


/**
 * author：T-Baymax on 2018/3/10 10:04
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */

abstract class CoreBaseLazyFragment<P : CoreBasePresenter<*, *>, E : CoreBaseModel> : Fragment() {


    var mPresenter: P? = null
    var mModel: E? = null
    private var mInited = false
    private var mSavedInstanceState: Bundle? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mSavedInstanceState = savedInstanceState
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
        if (savedInstanceState == null) {
            if (!isHidden) {
                mInited = true
                initLazyView(null)
            }
        } else {
            // isSupportHidden()仅在saveIns tanceState!=null时有意义,是库帮助记录Fragment状态的方法
            if (!isHidden) {
                mInited = true
                initLazyView(savedInstanceState)
            }
        }
    }

    private fun init() {

        mPresenter = ClassUtil.getT<P>(this, 0)
        mModel = ClassUtil.getT<E>(this, 1)
        if (this is CoreBaseView) mPresenter!!.attachVM(this, mModel!!)

    }

    override fun onHiddenChanged(hidden: Boolean) {
        super.onHiddenChanged(hidden)
        if (!mInited && !hidden) {
            mInited = true
            initLazyView(mSavedInstanceState)
        }
    }

    /**
     * 结束是注销Presenter
     */
    override fun onDestroy() {
        super.onDestroy()
        if (mPresenter != null) mPresenter!!.detachVM()
    }

    /**
     * 懒加载
     */
    protected abstract fun initLazyView(savedInstanceState: Bundle?)

}
