package com.wt.performance.monitor

import android.content.Context

/**
 *
 * @author bruce jing
 * @date 2021/9/4
 */
open class PerformanceMonitor{

    private var mContext:Context? = null
    companion object {

        val instance: PerformanceMonitor by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            PerformanceMonitor()
        }

    }

    fun init(context:Context){
        mContext = context
    }

}