package com.miss.lib_base.base.model

import io.reactivex.Observable
import io.reactivex.Observer
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit


/**
 *  author : 唐鹏聪
 *  date : 2022/5/30 21:52
 *  description :
 *
 *      model层负责数据提供：
 *      1. 本地数据，包括数据库
 *      2. 网络数据，网络请求
 *
 */
open class BaseModel : IModel {

    private val mCompositeDisposable by lazy { CompositeDisposable() }

    protected open fun <T> execute(observable: Observable<T>, callback: ExecuteCallback<T>) {
        observable
            .throttleFirst(500, TimeUnit.MILLISECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : Observer<T> {
                override fun onSubscribe(d: Disposable) {
                    addDisposable(d)
                }

                override fun onNext(t: T) {
                    callback.onSuccess(t)
                }

                override fun onError(e: Throwable) {
                    callback.onFail(e.message ?: "11")
                }

                override fun onComplete() {

                }

            })
    }

    interface ExecuteCallback<T>{
        fun onSuccess(t: T)
        fun onFail(msg: String)
    }


    fun addDisposable(disposable: Disposable) {
        mCompositeDisposable.add(disposable)
    }

    override fun onCleared() {
        if (!mCompositeDisposable.isDisposed) {
            mCompositeDisposable.clear()
        }
    }

}