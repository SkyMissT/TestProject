package com.miss.lib_base.util

import androidx.annotation.NonNull
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer

/**
 *      1.解决数据倒灌问题
 *      2.允许多观察者消费
 *      3.可以通过 clear 方法手动将消息从内存中移除
 *
 *  author : 唐鹏聪
 *  date : 2022/6/15 19:32
 *  description :
 */
class ProtectedUnPeekLiveData<T> : LiveData<T>() {

    private val observers by lazy { HashMap<Int, Boolean>() }

    fun observerInActivity(@Nullable activity: AppCompatActivity, @Nullable observer: Observer<in T>) {
        val storeId = System.identityHashCode(activity.viewModelStore)
        observer(storeId, activity, observer)
    }

    fun observerInFragment(@Nullable fragment: Fragment, @Nullable observer: Observer<in T>) {
        val storeId = System.identityHashCode(fragment.viewModelStore)
        observer(storeId, fragment, observer)
    }

    private fun observer(
        @Nullable storeId: Int,
        @Nullable owner: LifecycleOwner,
        @NonNull observer: Observer<in T>
    ) {
        if (!observers.containsKey(storeId)) {
            observers[storeId] = true
        }
        super.observe(owner, Observer<T> {
            if (!observers[storeId]!!) {
                observers[storeId] = true
                if (it != null) {
                    observer.onChanged(it)
                }
            }
        })
    }

    /**
     *      override setValue, don't receive null by default
     * @param value
     */
    override fun setValue(value: T) {
        if (value != null) {
            for (it in observers.entries) {
                it.setValue(false)
            }
            super.setValue(value)
        }
    }

    fun clear() {
        super.setValue(null)
    }

}