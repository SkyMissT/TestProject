package com.miss.lib_base.base.ui

import android.view.View
import androidx.databinding.ViewDataBinding
import com.miss.lib_base.base.viewmodel.BaseViewModel
import com.miss.lib_base.util.inflateBindingWithGeneric

/**
 *  author : 唐鹏聪
 *  date : 2022/6/17 11:47
 *  description :
 *
 *          在绑定VM的基础上绑定 DataBinding
 *
 */
abstract class BaseVmDbActivity<VM : BaseViewModel, DB : ViewDataBinding> : BaseVmActivity<VM>() {

    override fun layoutId(): Int = 0

    lateinit var mDatabind: DB

    /**
     * 创建DataBinding
     */
    override fun initViewBind(): View? {
        mDatabind = inflateBindingWithGeneric(layoutInflater)
        return mDatabind.root
    }

}