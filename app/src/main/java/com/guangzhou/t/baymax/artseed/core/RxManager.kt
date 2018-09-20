package com.guangzhou.t.baymax.artseed.core

import org.reactivestreams.Subscription

import java.util.HashMap

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.functions.Consumer

/**
 * 用于管理RxBus的事件和Rxjava相关代码的生命周期处理
 * author：T-Baymax on 2018/09/08 09:26
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */
class RxManager {

    var mRxBus = RxBus.rxBus()
    private val mObservables = HashMap<String, Observable<*>>()// 管理观察源
    private val mCompositeSubscription = CompositeDisposable()// 管理订阅者者


    fun on(eventName: String, action1: Consumer<Any>) {
        val mObservable = mRxBus.register<Any>(eventName)
        mObservables[eventName] = mObservable
        mCompositeSubscription.add(mObservable.observeOn(AndroidSchedulers.mainThread())
                .subscribe(action1, Consumer{ e -> e.printStackTrace() }))
    }

    fun add(m: Disposable) {
        mCompositeSubscription.add(m)
    }

    fun clear() {
        mCompositeSubscription.dispose()// 取消订阅
        for ((key, value) in mObservables)
            mRxBus.unregister(key, value)// 移除观察
    }

    fun post(tag: Any, content: Any) {
        mRxBus.post(tag, content)
    }


}