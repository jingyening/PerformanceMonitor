package com.wt.performance.monitor.statistic

import android.util.Log
import com.openos.statistics.WTClickAgent
import com.wt.performance.monitor.mGlobalContext

/**
 *
 * @author bruce jing
 * @date 2021/9/7
 */
class MonitorStatisticUtil {

  companion object {

    private const val TAG = "MonitorStatisticUtil"

    /**
     * 性能监控特有标识
     */
    const val FLAG = "pf_mt_st"


    /**
     * 发送事件
     *
     * @param eventId
     * @param map
     */
    fun onEvent(eventId: String, map: Map<String, String>) {
      Log.i(TAG, "发送事件::::::::$eventId")
      val entries = map.entries
      for ((key, value) in entries) {
        Log.d(TAG, "key::$key---value::$value")
      }
      WTClickAgent.onEvent(mGlobalContext, eventId, map)
    }

    fun onTimeStatisticEvent(eventId: String) {

    }

  }


}
class EventID {
  companion object {
    const val EVENT_TIME_STATISTIC = "ev_method_invoke_time"
  }
}
class StatisticKey{
  companion object{
    const val KEY_PKG_NAME = "pkg_name"
    const val KEY_CLASS_NAME = "c_name"
    const val KEY_TIMESTAMP = "tsp"
    const val KEY_BOOT_TIMESTAMP = "b_tsp"
  }

}
