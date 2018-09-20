package com.guangzhou.t.baymax.artseed.core.base

import com.guangzhou.t.baymax.artseed.core.data.repository.Repository

/**
 * 统一repository
 * author：T-Baymax on 2018/09/07 10:29
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
open class CoreBaseRepository :Cloneable {
    override fun clone(): Any  {
        var stu: Repository<*>? = null
        try {
            stu = super.clone() as Repository<*>
        } catch (e: CloneNotSupportedException) {
            e.printStackTrace()
        }

        return stu!!
    }
}
