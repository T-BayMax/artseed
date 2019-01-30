package com.bjike.t.baymax.artseed.core.data.repository

import android.database.Observable

import com.bjike.t.baymax.artseed.core.base.CoreBaseRepository

/**
 *
 * author：T-Baymax on 2018/09/08 16:27
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
abstract class Repository<T> : CoreBaseRepository() {
    var data: T? = null

    var param: Map<String, String>? = null

    abstract fun getPageAt(page: Int): Observable<Data<T>>
}
