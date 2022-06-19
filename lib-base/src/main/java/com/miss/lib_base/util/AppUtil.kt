package com.miss.lib_base.util

import android.annotation.SuppressLint
import android.content.Context

/**
 *
 *      全局 工具类
 *
 *  author : 唐鹏聪
 *  date : 2022/6/13 16:15
 *  description :
 */
@SuppressLint("StaticFieldLeak")
object AppUtil {

    private var sContext: Context? = null

    /**
     *      初始化工具类
     * @param context
     */
    fun init(context: Context){
        sContext = context.applicationContext
    }

    /**
     *      获取ApplicationContext
     * @return
     */
    fun getContext(): Context {
        if (sContext != null) {
            return sContext!!
        }
        throw NullPointerException("should be initialized in application")
    }


}