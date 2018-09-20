package com.guangzhou.t.baymax.artseed.mvp.model

import com.guangzhou.t.baymax.artseed.bean.UserBean
import com.guangzhou.t.baymax.artseed.core.data.entity.CoreDataResponse
import com.guangzhou.t.baymax.artseed.core.data.net.RxService
import com.guangzhou.t.baymax.artseed.core.utils.RxUtil
import com.guangzhou.t.baymax.artseed.mvp.contract.LoginContract
import com.guangzhou.t.baymax.artseed.networkapi.UserNetworkApi
import io.reactivex.Observable

/**
 * author：T-Baymax on 2018/9/15.
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
class LoginModel : LoginContract.Model {
    override fun login(fieldMap: MutableMap<String, String>): Observable<UserBean>? {
        return RxService.createApi(UserNetworkApi::class.java)
                .getUsersHot(fieldMap)
                .compose(RxUtil.rxSchedulerHelper())
                .compose(RxUtil.handleResult())

    }
}