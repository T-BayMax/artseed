package com.guangzhou.t.baymax.artseed.mvp.contract

import com.guangzhou.t.baymax.artseed.bean.UserBean
import com.guangzhou.t.baymax.artseed.core.base.CoreBaseModel
import com.guangzhou.t.baymax.artseed.core.base.CoreBasePresenter
import com.guangzhou.t.baymax.artseed.core.base.CoreBaseView
import com.guangzhou.t.baymax.artseed.core.data.entity.CoreDataResponse
import io.reactivex.Observable

/**
 * author：T-Baymax on 2018/3/10 11:50
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
interface LoginContract {
    interface View : CoreBaseView {

        fun login(results: UserBean)
    }

    interface Model : CoreBaseModel {
        fun login(fieldMap: MutableMap<String, String>): Observable<UserBean>?
    }

    abstract class Presenter : CoreBasePresenter<Model, View>() {
        abstract fun login(fieldMap: MutableMap<String, String>)
    }
}