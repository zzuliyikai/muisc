package com.hanshow.myapplication.base.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.hanshow.myapplication.api.ErrorType
import com.hanshow.myapplication.api.RemoteEventEmitter
import com.hanshow.myapplication.api.StatusEvent

/**
 * Created by yikai on 2022/6/1.
 * Describe:
 */
abstract class BaseViewModel : ViewModel(), RemoteEventEmitter {
    var errorInfo = MutableLiveData<Pair<Int, String>>()
    val loadingStatsLiveData = MutableLiveData<StatusEvent>()

    override fun onError(code: Int, msg: String, errorType: ErrorType) {
        errorInfo.postValue(Pair(code, msg))
    }

    override fun onEvent(event: StatusEvent) {
        loadingStatsLiveData.postValue(event)
    }
}