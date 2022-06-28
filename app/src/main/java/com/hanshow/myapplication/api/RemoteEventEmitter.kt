package com.hanshow.myapplication.api

/**
 * Created by yikai on 2022/6/1.
 * Describe:
 */
interface RemoteEventEmitter {
    fun onError(code: Int, msg: String, errorType: ErrorType)
    fun onEvent(event: StatusEvent)
}