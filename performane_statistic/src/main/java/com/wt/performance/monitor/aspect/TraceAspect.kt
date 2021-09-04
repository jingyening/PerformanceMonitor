package com.wt.performance.monitor.aspect

import android.annotation.TargetApi
import android.os.Build
import android.os.Trace
import android.util.Log
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before

/**
 *
 * @author bruce jing
 * @date 2021/8/10
 */
@Aspect
class TraceAspect {
    companion object {
        const val TAG = "TraceAspect"
    }

    //@Before("execution(@com.sample.annotation.TimeStatistic * *(..))")
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @Before("@annotation(com.wt.performance.monitor.annotation.Trace)")
    fun before(joinPoint: JoinPoint) {
        Trace.beginSection(joinPoint.signature.toString())
        Log.d(TAG, joinPoint.signature.toString())
    }

    /**
     * hook method when it's called out.
     */
    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    @After("@annotation(com.wt.performance.monitor.annotation.Trace)")
    fun after() {
        Trace.endSection()
    }


}