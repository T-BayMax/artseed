package com.guangzhou.t.baymax.artseed.mvp.presenter

import com.guangzhou.t.baymax.artseed.core.base.CoreBasePresenter
import com.guangzhou.t.baymax.artseed.core.utils.ErrorInfoUtils
import com.guangzhou.t.baymax.artseed.mvp.contract.LoginContract
import com.guangzhou.t.baymax.artseed.mvp.model.LoginModel

/**
 * author：T-Baymax on 2018/9/15.
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
class LoginPresenter : LoginContract.Presenter() {

    override fun login(fieldMap: MutableMap<String, String>) {
        mRxManager.add(mModel!!.login(fieldMap)!!
                .subscribe(
                        { data -> mView!!.login(data) },
                        { e -> mView!!.showError(e.message.toString()) }
                ))
    }
}