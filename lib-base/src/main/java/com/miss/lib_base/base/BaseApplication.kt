package com.miss.lib_base.base

import android.app.Activity
import android.app.Application
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import com.miss.lib_base.net.manager.NetworkStateReceive
import com.miss.lib_base.util.AppUtil

/**
 *  author : 唐鹏聪
 *  date : 2022/6/18 17:09
 *  description :
 */
open class BaseApplication : Application() {

    private val mNetworkStateReceive by lazy { NetworkStateReceive() }

    override fun onCreate() {
        super.onCreate()
        setApplication(this)
        init()
    }

    private fun init() {
        //  注册网络监听
        registerReceiver(
            mNetworkStateReceive,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    companion object{

        private var sInstance: Application? = null

        /**
         * 当主工程没有继承BaseApplication时，可以使用setApplication方法初始化BaseApplication
         *
         * @param application
         */
        @Synchronized
        fun setApplication(application: Application) {
            sInstance = application
            //初始化工具类
            AppUtil.init(application)
            //注册监听每个activity的生命周期,便于堆栈式管理
            application.registerActivityLifecycleCallbacks(object : ActivityLifecycleCallbacks {
                override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
                    AppManager.addActivity(activity)
                }

                override fun onActivityStarted(activity: Activity) {}
                override fun onActivityResumed(activity: Activity) {}
                override fun onActivityPaused(activity: Activity) {}
                override fun onActivityStopped(activity: Activity) {}
                override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}
                override fun onActivityDestroyed(activity: Activity) {
                    AppManager.removeActivity(activity)
                }
            })
        }
    }


}