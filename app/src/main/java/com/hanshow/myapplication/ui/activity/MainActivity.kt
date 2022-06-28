package com.hanshow.myapplication.ui.activity


import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.lifecycleScope
import com.hanshow.myapplication.R
import com.hanshow.myapplication.api.DataRepository
import com.hanshow.myapplication.api.ErrorType
import com.hanshow.myapplication.api.RemoteEventEmitter
import com.hanshow.myapplication.api.StatusEvent
import com.hanshow.myapplication.base.ui.BaseActivity
import com.hanshow.myapplication.base.ui.BaseEmptyActivity
import com.hanshow.myapplication.bean.Music
import com.hanshow.myapplication.databinding.ActivityMainBinding
import com.hanshow.myapplication.viewmodel.MainViewModel
import kotlinx.coroutines.*
import kotlin.coroutines.*

class MainActivity : BaseActivity<MainViewModel, ActivityMainBinding>() {

    override fun getLayoutId(): Int {
        return R.layout.activity_main
    }

    override fun initViewModel(): MainViewModel {
        return MainViewModel()
    }

    override fun initData(savedInstanceState: Bundle?) {

        dataBinding.send.setOnClickListener {
            testRunBlocking()
        }
    }

    private fun testRunBlocking() = runBlocking {

        val scope = CoroutineScope(Dispatchers.IO)

        scope.launch {
            try {
                delay(2000)
                Log.d("yikai", "hello!")
            } finally {
                withContext(NonCancellable) {
                    Log.d("yikai", "success!")
                }
            }

            withContext(Dispatchers.IO){

            }
        }

     //   delay(100)
        scope.cancel()


    }

}
