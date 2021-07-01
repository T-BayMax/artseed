package com.guangzhou.t.baymax.artseed.core

import androidx.annotation.NonNull
import java.util.ArrayList
import java.util.concurrent.ConcurrentHashMap

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.Consumer
import io.reactivex.subjects.PublishSubject
import io.reactivex.subjects.Subject

/**
 * 用RxJava实现的EventBus
 * author：T-Baymax on 2018/09/08 09:26
 * mail：baixianhong_aj@163.com
 * authorization：bjike.com
 */

class RxBus private constructor() {

    private val subjectMapper = ConcurrentHashMap<Any, MutableList<Subject<Any>>>()

    /**
     * 注册事件源
     *
     * @param tag
     * @return
     */
    fun <T> register(tag: Any): Observable<T> {
        var subjectList: MutableList<Subject<Any>>? = subjectMapper[tag]
        if (null == subjectList) {
            subjectList = ArrayList()
            subjectMapper[tag] = subjectList
        }
        val subject = PublishSubject.create<T>()
        subjectList.add(subject as Subject<Any>)
        return subject
    }

    fun unregister(tag: Any) {
        val subjects = subjectMapper[tag]
        if (null != subjects) {
            subjectMapper.remove(tag)
        }
    }

    /**
     * 取消监听
     *
     * @param tag
     * @param observable
     * @return
     */
    fun unregister(tag: Any,
                   observable: Observable<*>): RxBus {
        if (null == observable)
            return rxBus()
        val subjects = subjectMapper[tag]
        if (null != subjects) {
            subjects.remove(observable as Subject<*>)
            if (isEmpty(subjects)) {
                subjectMapper.remove(tag)

            }
        }
        return rxBus()
    }

    /**
     * 订阅事件源
     *
     * @param mObservable
     * @param mAction1
     * @return
     */
    fun OnEvent(mObservable: Observable<*>, consumer: Consumer<Any>): RxBus {
        mObservable.observeOn(AndroidSchedulers.mainThread()).subscribe(consumer, Consumer { e -> e.printStackTrace() })
        return rxBus()
    }

    fun post(@NonNull content: Any) {
        post(content.javaClass.name, content)
    }

    /**
     * 触发事件
     *
     * @param content
     */
    fun post(tag: Any, @NonNull content: Any) {

        val subjectList = subjectMapper[tag]
        if (!isEmpty(subjectList)) {
            for (subject in subjectList!!.iterator()) subject.onNext(content)
        }
    }

    companion object {
        private var instance: RxBus? = null

        @Synchronized
        fun rxBus(): RxBus {
            if (null == instance) {
                instance = RxBus()
            }
            return instance!!
        }

        fun isEmpty(collection: Collection<Subject<*>>?): Boolean {
            return null == collection || collection.isEmpty()
        }
    }
}