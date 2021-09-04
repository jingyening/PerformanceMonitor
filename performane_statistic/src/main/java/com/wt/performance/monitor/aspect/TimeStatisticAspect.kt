package com.wt.performance.monitor.aspect

import android.annotation.TargetApi
import android.os.Build
import android.os.SystemClock
import android.os.Trace
import android.util.Log
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.ProceedingJoinPoint
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut

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
        Log.d(
            TAG,
            "onTimeStatisticAround == " + joinPoint.signature.toString() + " ,timeStamp = " + System.currentTimeMillis() + " ,boot time = " + SystemClock.elapsedRealtime()
        )
        try {
            joinPoint.proceed()
        } catch (t: Throwable) {
        }
    }

}