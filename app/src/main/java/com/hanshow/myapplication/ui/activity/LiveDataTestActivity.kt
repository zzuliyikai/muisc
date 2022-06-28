package com.hanshow.myapplication.ui.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import com.hanshow.myapplication.R

class LiveDataTestActivity : AppCompatActivity() {

    private val liveData by lazy {
        MutableLiveData<String>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live_data_test)


    }

    override fun onStart() {
        super.onStart()
        Log.d("yikai", "onStart")
        liveData.value = "onCreate"
    }

    override fun onResume() {
        super.onResume()
        Log.d("yikai", "onResume")
        liveData.value = "onResume"
    }

    override fun onPause() {
        super.onPause()
        Log.d("yikai", "onPause")
        liveData.value = "onPause"
    }

    override fun onStop() {
        super.onStop()
        Log.d("yikai", "onStop")
        liveData.value = "onStop"
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.d("yikai", "onDestroy")
        liveData.value = "onDestroy"
    }

}