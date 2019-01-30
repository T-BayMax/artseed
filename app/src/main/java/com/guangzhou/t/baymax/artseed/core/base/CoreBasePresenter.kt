package com.bjike.t.baymax.artseed.core.base

import com.bjike.t.baymax.artseed.core.RxManager


/**
 * 统一presenter
 * author：T-Baymax on 2018/09/08 10:18
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
open class CoreBasePresenter<out M : CoreBaseModel, out T : CoreBaseView> {
    var mModel: @UnsafeVariance M? = null
    var mView: @UnsafeVariance T? = null
    var mRxManager = RxManager()

    fun attachVM(v: @UnsafeVariance T, m: @UnsafeVariance M) {
        this.mView = v
        this.mModel = m
    }

    fun detachVM() {
        mRxManager.clear()
        mView = null
        mModel = null
    }

}
