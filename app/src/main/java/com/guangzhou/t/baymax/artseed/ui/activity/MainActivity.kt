package com.guangzhou.t.baymax.artseed.ui.activity

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.LinearLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.guangzhou.t.baymax.artseed.R
import com.google.gson.Gson
import com.just.agentweb.AgentWeb
import com.just.agentweb.DefaultWebClient
import com.just.agentweb.PermissionInterceptor

class MainActivity : AppCompatActivity() {
private lateinit var container:LinearLayout
    private lateinit var mAgentWeb: AgentWeb
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()
    }

    private fun initView() {
        container=findViewById(R.id.container)
        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(container, LinearLayout.LayoutParams(-1, -1))
                .useDefaultIndicator()
                .setWebChromeClient(mWebChromeClient)
                // .setWebViewClient(mWebViewClient)
                .setMainFrameErrorView(R.layout.agentweb_error_page, -1)
                .setSecurityType(AgentWeb.SecurityType.STRICT_CHECK)
                .setPermissionInterceptor(mPermissionInterceptor) //权限拦截 2.0.0 加入。
                // .setWebLayout(WebLayout(this))
                .setOpenOtherPageWays(DefaultWebClient.OpenOtherPageWays.ASK)//打开其他应用时，弹窗咨询用户是否前往其他应用
                .interceptUnkownUrl() //拦截找不到相关页面的Scheme
                .createAgentWeb()
                .ready()
                .go("https://customersmanage.issp.bjike.com/index")
    }


    private val mWebViewClient = object : WebViewClient() {
        override fun shouldOverrideUrlLoading(view: WebView, request: WebResourceRequest): Boolean {
            return super.shouldOverrideUrlLoading(view, request)
        }

        override fun onPageStarted(view: WebView, url: String, favicon: Bitmap) {
            //do you  work
            Log.i("Info", "BaseWebActivity onPageStarted")
        }
    }
    private val mWebChromeClient = object : WebChromeClient() {
        override fun onProgressChanged(view: WebView, newProgress: Int) {
            //do you work
            //            Log.i("Info","onProgress:"+newProgress);
        }

        override fun onReceivedTitle(view: WebView, title: String) {
            super.onReceivedTitle(view, title)
            /*  if (mTitleTextView != null) {

                  mTitleTextView.setText(title)
              }*/
        }
    }

    private val mGson = Gson()
    protected var mPermissionInterceptor: PermissionInterceptor = PermissionInterceptor { url, permissions, action ->
        /**
         * PermissionInterceptor 能达到 url1 允许授权， url2 拒绝授权的效果。
         * AgentWeb 是用自己的权限机制的 ，true 该Url对应页面请求定位权限拦截 ，false 默认允许。
         * @param url
         * @param permissions
         * @param action
         * @return
         */
        Log.i("intercept", "mUrl:" + url + "  permission:" + mGson.toJson(permissions) + " action:" + action)
        false
    }
    private var clickTime = 0L
    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (!mAgentWeb.back()) {

                if (System.currentTimeMillis() - clickTime > 2000) {
                    Toast.makeText(applicationContext, "再次点击退出艺籽",
                            Toast.LENGTH_SHORT).show()
                    clickTime = System.currentTimeMillis()
                } else {
                    finish()

                }
            }
            return true

        }
        return super.onKeyDown(keyCode, event)
    }

}
