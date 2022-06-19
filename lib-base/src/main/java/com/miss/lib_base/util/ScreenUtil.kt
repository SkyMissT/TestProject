package com.miss.lib_base.util

import android.content.res.Configuration

/**
 *  author : 唐鹏聪
 *  date : 2022/6/18 18:40
 *  description :
 *
 *          屏幕相关工具类
 *
 */
object ScreenUtil {

    /**
     * 是否是   ipad
     * @param screenLayout
     *
     */
    fun isPad(screenLayout: Int): Boolean {
        return screenLayout and Configuration.SCREENLAYOUT_SIZE_MASK >= Configuration.SCREENLAYOUT_SIZE_LARGE
    }

}