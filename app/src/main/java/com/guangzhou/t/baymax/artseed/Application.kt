package com.guangzhou.t.baymax.artseed

import com.guangzhou.t.baymax.artseed.bean.UserBean
import com.guangzhou.t.baymax.artseed.core.CoreApp

/**
 * author：T-Baymax on 2018/9/15.
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
class Application:CoreApp() {
    companion object {
        var user: UserBean? = null
    }
    override fun setBaseUrl(): String {
        return resources.getString(R.string.base_url)
    }
}