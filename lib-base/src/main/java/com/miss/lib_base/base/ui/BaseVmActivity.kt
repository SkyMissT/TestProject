package com.miss.lib_base.base.ui

import android.content.Intent
import android.content.pm.ActivityInfo
import android.os.Bundle
import android.view.View
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.miss.lib_base.base.viewmodel.BaseViewModel
import com.miss.lib_base.net.manager.NetState
import com.miss.lib_base.net.manager.NetworkStateManager
import com.miss.lib_base.util.ScreenUtil
import com.miss.lib_base.util.getVmClazz
import com.miss.lib_base.util.notNull

/**
 *  author : 唐鹏聪
 *  date : 2022/6/16 15:13
 *  description :
 *
 *          MVVM activity 基类
 *          1.绑定ViewModel
 *
 */
abstract class BaseVmActivity<VM : BaseViewModel> : AppCompatActivity() {

    lateinit var mViewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        setOrientation()
        super.onCreate(savedInstanceState)
        setContentView(initViewBind())
        initViewBind().notNull({
            setContentView(it)
        },{
            setContentView(layoutId())
        })
        init(savedInstanceState)
    }

    private fun init(savedInstanceState: Bundle?) {
        mViewModel = createViewModel()
        registerUiChange()
        initView(savedInstanceState)
        createObserver()
        NetworkStateManager.instance.mNetworkStateCallback.observe(this, Observer {
            onNetworkStateChanged(it)
        })

    }

    /**
     *      register UI livedata
     */
    private fun registerUiChange() {
        //  close dialog
        mViewModel.loadingChange.showDialog.observe(this, Observer {
            showLoading(it)
        })
        //  close dialog
        mViewModel.loadingChange.dismissDialog.observe(this, Observer {
            dismissLoading()
        })
        // start activity
        mViewModel.loadingChange.startActivityEvent.observe(this, Observer {
            val clz = it!![BaseViewModel.ParameterField.CLASS] as Class<*>?
            val bundle = it[BaseViewModel.ParameterField.BUNDLE] as Bundle?
            startActivity(clz, bundle)
        })
    }

    open fun showLoading(message: String) {}

    open fun dismissLoading() {}

    /**
     * 网络变化监听 子类可重写
     */
    open fun onNetworkStateChanged(netState: NetState) {}

    /**
     *      livedata 在此初始化
     */
    abstract fun createObserver()

    abstract fun initView(savedInstanceState: Bundle?)

    abstract fun initViewBind(): View?

    @LayoutRes
    abstract fun layoutId(): Int


    private fun createViewModel(): VM = ViewModelProvider(this).get(getVmClazz(this))

    /**
     *      平板支持旋转，手机只支持竖屏
     */
    private fun setOrientation(){
        if (ScreenUtil.isPad(resources.configuration.screenLayout)) {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED)
        } else {
            setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT)
        }
    }

    /**
     * 跳转页面
     *
     * @param clz    所跳转的目的Activity类
     * @param bundle 跳转所携带的信息
     */
    fun startActivity(clz: Class<*>?, bundle: Bundle?) {
        val intent = Intent(this, clz)
        if (bundle != null) {
            intent.putExtras(bundle)
        }
        startActivity(intent)
    }
}