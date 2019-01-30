package com.bjike.t.baymax.artseed.core.utils

import com.bjike.t.baymax.artseed.core.data.entity.CoreDataResponse
import com.bjike.t.baymax.artseed.core.data.net.CoreApiException
import com.bjike.t.baymax.artseed.core.data.net.HttpResponseFunc

import io.reactivex.Observable
import io.reactivex.ObservableTransformer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


object RxUtil {

    /**
     * 统一线程处理
     *
     * @param <T>
     * @return
    </T> */
    fun <T> rxSchedulerHelper(): ObservableTransformer<T, T> {    //compose简化线程
        return ObservableTransformer{ observable ->
            observable.subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
        }
    }

    /**
     * 统一返回结果处理
     *
     * @param <T>
     * @return
    </T> */
    fun <T> handleResult(): ObservableTransformer<CoreDataResponse<T>, T> {   //compose判断结果
        return ObservableTransformer{ httpResponseObservable ->
            httpResponseObservable.flatMap { tCoreDataResponse ->
                if (tCoreDataResponse.code == 200) {
                    val data=tCoreDataResponse.data
                    if (data is CoreDataResponse<*>){
                        createData(tCoreDataResponse as T)
                    }else
                        createData(tCoreDataResponse.data)
                } else {
                    Observable.error(CoreApiException(tCoreDataResponse.msg!!))//返回自定义错误
                }
            }.onErrorResumeNext (HttpResponseFunc())
        }
    }

    /**
     * 生成Observable
     *
     * @param <T>
     * @return
    </T> */
    fun <T> createData(t: T?): Observable<T> {
        return Observable.create { emitter ->
            try {
                emitter.onNext(t!!)
            } catch (e: Exception) {
                emitter.onError(e)
            }
        }
    }
}
