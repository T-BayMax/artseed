package com.bjike.t.baymax.artseed.core.data.net

import com.bjike.t.baymax.artseed.core.CoreApp

import java.io.File

import okhttp3.Cache

/**
 * 缓存大小
 * author：T-Baymax on 2018/09/07 15:08
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
object HttpCache {

    private val HTTP_RESPONSE_DISK_CACHE_MAX_SIZE = 50 * 1024 * 1024

    val cache: Cache
        get() = Cache(File(CoreApp.appContext.getCacheDir().getAbsolutePath() + File.separator + "data/NetCache"),
                HTTP_RESPONSE_DISK_CACHE_MAX_SIZE.toLong())
}
