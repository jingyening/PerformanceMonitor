package com.wt.performance.monitor.annotation


/**
 *
 * @author bruce jing
 * @date 2021/8/10
 */


@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FUNCTION)
@MustBeDocumented//文档生成时，该注解将被包含在javadoc中，可去掉
annotation class Trace()
