package com.guangzhou.t.baymax.artseed.core.data.entity

/**
 * 服务器返回类型
 * author：T-Baymax on 2018/09/10 11:56
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
class CoreDataResponse<T> {
  var code: Int = 0
  var msg: String? = null
  var data: T? = null

  override fun toString(): String {
    return "CoreDataResponse{" +
            "code=" + code +
            ", msg='" + msg + '\''.toString() +
            ", data=" + data +
            '}'.toString()
  }
}