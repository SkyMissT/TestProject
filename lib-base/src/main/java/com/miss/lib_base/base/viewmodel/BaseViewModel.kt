package com.miss.lib_base.base.viewmodel

import android.os.Bundle
import androidx.lifecycle.ViewModel
import com.miss.lib_base.base.model.BaseModel
import com.miss.lib_base.util.SingleLiveEvent
import com.miss.lib_base.util.notNull

/**
 *  author : 唐鹏聪
 *  date : 2022/5/30 22:06
 *  description :
 *      iewModel的基类 使用ViewModel类，放弃AndroidViewModel，原因：用处不大 完全有其他方式获取Application上下文
 */
open class BaseViewModel : ViewModel(), IBaseViewModel {

    val loadingChange by lazy { UiLoadingChange() }


    /**
     * 内置封装好的可通知Activity/fragment 显示隐藏加载框
     */
    inner class UiLoadingChange {
        //显示加载框
        val showDialog by lazy { SingleLiveEvent<String>(true) }
        //隐藏
        val dismissDialog by lazy { SingleLiveEvent<Boolean>(true) }
        val startActivityEvent by lazy { SingleLiveEvent<Map<String, Any>>(true) }
    }

    /**
     *      ViewModel销毁时调用，释放资源
     */
    override fun onCleared() {
        super.onCleared()
    }

    /**
     * 跳转页面
     *
     * @param clz 所跳转的目的Activity类
     */
    fun startActivity(clz: Class<*>) {
        startActivity(clz, null)
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun startActivity(clz: Class<*>, bundle: Bundle?) {
        val params: MutableMap<String, Any> = HashMap()
        params[ParameterField.CLASS] = clz
        if (bundle != null) {
            params[ParameterField.BUNDLE] = bundle
        }
        loadingChange.startActivityEvent.postValue(params)
    }


    // ====================== 生命周期处理 ======================

    override fun onCreate() {

    }

    override fun onDestroy() {

    }

    override fun onStart() {

    }

    override fun onStop() {

    }

    override fun onResume() {

    }

    override fun onPause() {

    }

    override fun onAny() {

    }

    object ParameterField {
        var CLASS = "CLASS"
        var CANONICAL_NAME = "CANONICAL_NAME"
        var BUNDLE = "BUNDLE"
    }

}