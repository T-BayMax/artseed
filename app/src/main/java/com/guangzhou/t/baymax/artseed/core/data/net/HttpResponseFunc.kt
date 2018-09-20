package com.guangzhou.t.baymax.artseed.core.data.net

import com.guangzhou.t.baymax.artseed.core.utils.ErrorInfoUtils
import io.reactivex.Observable

import io.reactivex.functions.Function

/**
 * 服务器返回错误信息
 * author：T-Baymax on 2018/9/15.
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
class HttpResponseFunc<T> :Function<Throwable,Observable<T>>{
    override fun apply(t: Throwable): Observable<T> {
        return ErrorInfoUtils.parseHttpErrorInfo<T>(t)!!
    }
}