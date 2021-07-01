package com.guangzhou.t.baymax.artseed.core

import android.app.Application
import android.content.Context
import android.content.res.Resources
import androidx.multidex.MultiDex


abstract class CoreApp : Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this);
        instance = this
    }

    abstract fun setBaseUrl(): String

    companion object {
        @get:Synchronized
        var instance: CoreApp? = null
            private set

        val appContext: Context
            get() = instance!!.applicationContext

        val appResources: Resources
            get() = instance!!.resources
    }
}
