package com.bjike.t.baymax.artseed.core.utils

import android.content.Context

import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

/**
 * cookie管理类
 * Created by T-BayMax on 2017/8/22.
 */

class CookieManger(context: Context) : CookieJar {

  init {
    mContext = context
    if (cookieStore == null) {
      cookieStore = PersistentCookieStore(mContext)
    }

  }

  override fun saveFromResponse(url: HttpUrl, cookies: List<Cookie>?) {
    if (cookies != null && cookies.isNotEmpty()) {
      for (item in cookies) {
        cookieStore!!.add(url, item)
      }
    }
  }

  override fun loadForRequest(url: HttpUrl): List<Cookie> {
    return cookieStore!![url]
  }

  internal class Customer(var userID: String?, var token: String?)

  companion object {


    var APP_PLATFORM = "app-platform"


    private lateinit var mContext: Context

    private var cookieStore: PersistentCookieStore? = null
  }
}
