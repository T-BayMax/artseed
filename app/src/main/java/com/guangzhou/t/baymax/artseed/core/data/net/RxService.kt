package com.bjike.t.baymax.artseed.core.data.net

import com.bjike.t.baymax.artseed.core.CoreApp
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory

import java.util.concurrent.TimeUnit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.net.ssl.HostnameVerifier
import javax.net.ssl.SSLSession

/**
 * 设置OkHttpClient
 * author：T-Baymax on 2018/09/08 15:03
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
object RxService {
    private val TIMEOUT_READ = 20
    private val TIMEOUT_CONNECTION = 10
    private val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
    private val cacheInterceptor = CacheInterceptor()
  /*  val certficates = intArrayOf(R.raw.issp)//保存raw资源文件的证书
    val certificate = appResources.openRawResource(certficates[0])
    val sslParams = HttpsUtils.getSslSocketFactory(null, certificate, null)*/
    private val okHttpClient = OkHttpClient.Builder()
            //SSL证书
          /*  .sslSocketFactory(sslParams.sSLSocketFactory, sslParams.trustManager)
           //管理cookie
          .cookieJar(CookieManger(appContext))*/
          //设置验证证书
          .hostnameVerifier { _: String, _: SSLSession ->
              true
          }
          //打印日志
            .addInterceptor(interceptor)
            //设置Cache
            .addNetworkInterceptor(cacheInterceptor)//缓存方面需要加入这个拦截器
           // .addInterceptor(cacheInterceptor)
            //.cache(HttpCache.cache)
            //time out
            .connectTimeout(TIMEOUT_CONNECTION.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT_READ.toLong(), TimeUnit.SECONDS)
            //失败重连
            .retryOnConnectionFailure(true)
            .build()

    fun <T> createApi(clazz: Class<T>): T {
        return createApi(clazz, CoreApp.instance!!.setBaseUrl())
    }

    fun <T> createApi(clazz: Class<T>, url: String): T {
        val retrofit = Retrofit.Builder()
                .baseUrl(url)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(clazz)
    }
}

