package com.wt.performance.monitor.platform

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.wt.performance.monitor.annotation.TimeStatistic
import com.wt.performance.monitor.annotation.Trace

class MainActivity : AppCompatActivity() {

  companion object{
    const val TAG = "MainActivity"
  }

  @TimeStatistic
  @Trace
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)
    Log.d(TAG,"onCreate")
  }

  @TimeStatistic
  @Trace
  override fun onResume() {
    super.onResume()
  }


}