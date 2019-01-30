package com.bjike.t.baymax.artseed.core.data.net

import com.bjike.t.baymax.artseed.core.CoreApp
import com.bjike.t.baymax.artseed.core.utils.NetUtils
import com.bjike.t.baymax.artseed.core.utils.PreferenceService

import java.io.IOException

import okhttp3.CacheControl
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

/**
 * 设置网络数据请求缓存
 * author：T-Baymax on 2018/09/07 14:12
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
class CacheInterceptor : Interceptor {
    private val USER_TOKEN = "Authorization"
    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {

        var preference = PreferenceService(CoreApp.appContext)
        var usertoken = preference.getSetting("usertoken")
        var request = chain.request()
        /*request= request.newBuilder()
                .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                .addHeader("Connection", "close").build()*/

        if(usertoken.isNullOrBlank() || request.header(USER_TOKEN) == null){
            request=request.newBuilder().addHeader(USER_TOKEN, "token $usertoken").build()
        }
        if (request.header("Connection") == null||request.header("Content-Type") == null){
            request= request.newBuilder()
                    .addHeader("Content-Type", "application/x-www-form-urlencoded; charset=UTF-8")
                    .addHeader("Connection", "close").build()
        }
/*
        if (!usertoken.isNullOrBlank()) {
            request.addHeader("Authorization", "token $usertoken")
            request.addHeader("token",usertoken)
        }*/
        if (!NetUtils.isConnected(CoreApp.appContext)) {
            request=request.newBuilder().cacheControl(CacheControl.FORCE_CACHE).build()

        }
        val response = chain.proceed(request)
        if (NetUtils.isConnected(CoreApp.appContext)) {
            val maxAge = 0
            // 有网络时, 不缓存, 最大保存时长为0
            response.newBuilder()
                    .header("Cache-Control", "public, max-age=$maxAge")
                    .removeHeader("Pragma")
                    .build()
        } else {
            // 无网络时，设置超时为4周
            val maxStale = 60 * 60 * 24 * 28
            response.newBuilder()
                    .header("Cache-Control", "public, only-if-cached, max-stale=$maxStale")
                    .removeHeader("Pragma")
                    .build()
        }
        return response
    }
}
