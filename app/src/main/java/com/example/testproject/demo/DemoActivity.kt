package com.example.testproject.demo

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.testproject.R
import com.example.testproject.databinding.ActivityDemoBinding
import com.miss.lib_base.base.ui.BaseVmVbActivity
import com.miss.lib_base.net.manager.NetState
import io.reactivex.Observable

/**
 *  author : 唐鹏聪
 *  date : 2022/6/19 23:06
 *  description :
 */
class DemoActivity: BaseVmVbActivity<DemoViewModel, ActivityDemoBinding>(),View.OnClickListener {

    private val mContext by lazy { this@DemoActivity }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun createObserver() {
        mViewModel.requestResult.observe(this, androidx.lifecycle.Observer {
            //  处理受到的数据
            Toast.makeText(mContext, it, Toast.LENGTH_SHORT).show()
        })
    }

    override fun initView(savedInstanceState: Bundle?) {
        mViewBind.btn.setOnClickListener {
            var observable1: Observable<String>?=null
            mViewModel.request(observable1!!)
        }
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.btn -> {
                //  点击监听
            }
        }
    }

    override fun onNetworkStateChanged(netState: NetState) {
        super.onNetworkStateChanged(netState)
        if (!netState.isSuccess) {
            //  todo ： connect net failed
        }
    }
}