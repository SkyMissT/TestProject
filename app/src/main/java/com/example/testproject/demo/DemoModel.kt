package com.example.testproject.demo

import com.miss.lib_base.base.model.BaseModel
import io.reactivex.Observable

/**
 *  author : 唐鹏聪
 *  date : 2022/6/19 22:58
 *  description :
 */
class DemoModel : BaseModel() {

    /**
     *      接口请求
     *
     * @param observable
     * @param callback
     */
    fun request(observable: Observable<String>, callback: ExecuteCallback<String>) {
        execute(observable, callback)
    }

    fun test(){

    }

}