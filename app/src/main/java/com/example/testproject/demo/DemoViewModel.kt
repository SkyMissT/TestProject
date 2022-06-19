package com.example.testproject.demo

import com.miss.lib_base.base.model.BaseModel
import com.miss.lib_base.base.viewmodel.BaseViewModel
import com.miss.lib_base.util.SingleLiveEvent
import io.reactivex.Observable

/**
 *  author : 唐鹏聪
 *  date : 2022/6/19 22:58
 *  description :
 */
class DemoViewModel : BaseViewModel() {

    private val mModel by lazy { DemoModel() }

    val requestResult by lazy { SingleLiveEvent<String>() }

    fun request(observable: Observable<String>) {
        mModel.request(observable,object : BaseModel.ExecuteCallback<String> {
            override fun onSuccess(t: String) {
                requestResult.value = t
            }

            override fun onFail(msg: String) {
                requestResult.value = msg
            }

        })
    }


    override fun onCleared() {
        super.onCleared()
        mModel.onCleared()
    }

}