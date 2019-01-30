package com.bjike.t.baymax.artseed.core.utils

import com.bjike.t.baymax.artseed.core.data.entity.CoreDataResponse
import com.bjike.t.baymax.artseed.core.data.net.CoreApiException
import com.bjike.t.baymax.artseed.core.data.net.CoreErrorConstants
import com.google.gson.Gson
import io.reactivex.Observable

import java.net.UnknownHostException

import retrofit2.HttpException
import java.net.SocketTimeoutException

object ErrorInfoUtils {

    /**
     * 解析服务器错误信息
     */
    fun <T> parseHttpErrorInfo(throwable: Throwable): Observable<T>? {
        var errorInfo = throwable.message

        if (throwable is HttpException) {
            // 如果是Retrofit的Http错误,则转换类型,获取信息
            val responseBody = throwable.response().errorBody()
            val type = responseBody!!.contentType()
            if (null != type) {
                // 如果是application/json类型数据,则解析返回内容
                if (type.type() == "application" && type.subtype() == "json") {
                    try {
                        // 这里的返回内容是Bmob/AVOS/Parse等RestFul API文档中的错误代码和错误信息对象
                        val errorResponse = Gson().fromJson(
                                responseBody.string(), CoreDataResponse::class.java)

                        errorInfo = getLocalErrorInfo(errorResponse)
                    } catch (e: Exception) {
                        errorInfo = "程序发生错误，请重试！"
                        e.printStackTrace()
                    }

                }
            } else {
                errorInfo = "网络连接出错"
            }
        } else {
            if (throwable is UnknownHostException) {
                errorInfo = "无法连接到服务器"
            }else if (throwable is SocketTimeoutException){
                errorInfo = "请求超时，请确认网络连接后重试！"
            }else  if (throwable is CoreApiException){
               // errorInfo = "请求超时，请确认网络连接后重试！"
            }else{
                errorInfo = "发生未知错误"
            }
        }

        return Observable.error(CoreApiException(errorInfo!!))
    }

    /**
     * 获取本地预设错误信息
     */
    private fun getLocalErrorInfo(error: CoreDataResponse<*>): String? {
        val s = CoreErrorConstants.errors[error.code]
        return if (s.isNullOrBlank()) {
            error.msg
        } else {
            if (error.msg.isNullOrBlank()) {
                s
            } else
                error.msg
        }
    }
}
