package com.miss.lib_base.net.manager

import com.miss.lib_base.util.SingleLiveEvent


/**
 *  author : 唐鹏聪
 *  date : 2022/6/16 15:13
 *  description :
 */
class NetworkStateManager private constructor() {

    val mNetworkStateCallback = SingleLiveEvent<NetState>()

    companion object {
        val instance: NetworkStateManager by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            NetworkStateManager()
        }
    }

}