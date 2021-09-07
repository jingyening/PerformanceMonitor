package com.wt.performance.monitor.aspect

import android.annotation.TargetApi
import android.os.Build
import android.os.SystemClock
import android.os.Trace
import android.util.Log
import com.wt.performance.monitor.mGlobalContext
import com.wt.performance.monitor.statistic.EventID
import com.wt.performance.monitor.statistic.MonitorStatisticUtil
import com.wt.performance.monitor.statistic.StatisticKey
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.CodeSignature

/**
 *
 * @author bruce jing
 * @date 2021/8/10
 */
@Aspect
class TimeStatisticAspect {

    companion object{
        const val TAG = "TimeStatisticAspect"
    }

//    @TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
//    @Before("@annotation(com.wt.performance.monitor.annotation.TimeStatistic)")
//    fun before(joinPoint: JoinPoint) {
//        Trace.beginSection(joinPoint.signature.toString())
//        Log.d(
//            TAG,
//            "onTimeStatisticAround == " + joinPoint.signature.toString() + " ,timeStamp = " + System.currentTimeMillis() + " ,boot time = " + SystemClock.elapsedRealtime()
//        )
//    }

    ///**
    // * hook method when it's called out.
    // */
    //@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    //@After("execution(* **(..))")
    //public void after() {
    //    Trace.endSection();
    //}

    //@Pointcut("execution(* testadc(..))")
    //@Pointcut("execution(@com.sample.annotation.TimeStatistic * *(..))")

    ///**
    // * hook method when it's called out.
    // */
    //@TargetApi(Build.VERSION_CODES.JELLY_BEAN_MR2)
    //@After("execution(* **(..))")
    //public void after() {
    //    Trace.endSection();
    //}
    //@Pointcut("execution(* testadc(..))")
    //@Pointcut("execution(@com.sample.annotation.TimeStatistic * *(..))")
    @Pointcut("@annotation(com.wt.performance.monitor.annotation.TimeStatistic)")
    fun timeStatistic() {
    }


    @Around("timeStatistic()")
    fun onTimeStatisticAround(joinPoint: ProceedingJoinPoint) {
        var map = mutableMapOf<String,String>()
        map[StatisticKey.KEY_PKG_NAME] = mGlobalContext!!.applicationInfo.processName
        map[StatisticKey.KEY_CLASS_NAME] = joinPoint.signature.toString()
        map[StatisticKey.KEY_BOOT_TIMESTAMP] = SystemClock.elapsedRealtime().toString()
        map[StatisticKey.KEY_TIMESTAMP] = System.currentTimeMillis().toString()
        MonitorStatisticUtil.onEvent(EventID.EVENT_TIME_STATISTIC, map)
        var values = joinPoint.args
        var names = (joinPoint.signature as CodeSignature).parameterNames;
        Log.d(
            TAG,
            "onTimeStatisticAround == " + joinPoint.signature.toString() + " ,timeStamp = " + System.currentTimeMillis() + " ,boot time = " + SystemClock.elapsedRealtime()
        )
        names.forEachIndexed(){ i: Int, s: String ->
            Log.d(TAG,"onTimeStatisticAround $s = ${values[i]}")
        }
        try {
            joinPoint.proceed()
        } catch (t: Throwable) {
        }
    }

}