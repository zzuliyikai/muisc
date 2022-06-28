package com.hanshow.myapplication.base.repository

import com.google.gson.Gson
import com.hanshow.myapplication.api.ErrorType
import com.hanshow.myapplication.api.MusicStyleError
import com.hanshow.myapplication.api.RemoteEventEmitter
import com.hanshow.myapplication.api.StatusEvent
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

/**
 * Created by yikai on 2022/6/1.
 * Describe:
 */
abstract class BaseRemoteRepository {

    suspend fun <T> safeApiCall(
        emitter: RemoteEventEmitter?,
        callFunction: suspend () -> T
    ): T? {
        emitter?.onEvent(StatusEvent.LOADING)

        return try {
            val myObject = withContext(Dispatchers.IO) { callFunction.invoke() }
            emitter?.onEvent(StatusEvent.SUCCESS)
            myObject
        } catch (e: Exception) {
            withContext(Dispatchers.Main) {
                e.printStackTrace()
                when (e) {
                    is HttpException -> {
                        // fixme 后台返回的code >= 300,但是返回给了错误消息
                        val hanShowError = withContext(Dispatchers.IO) {
                            val body = e.response()?.errorBody()
                            if (body != null) {
                                try {
                                    Gson().fromJson(body.toString(), MusicStyleError::class.java)
                                } catch (e: Exception) {
                                    null
                                }
                            } else {
                                null
                            }
                        }
                        withContext(Dispatchers.Main) {
                            emitter?.onEvent(StatusEvent.ERROR)
                            when (hanShowError?.code) {
                                403 -> {
                                    emitter?.onError(403, "ip被禁止", ErrorType.BACKEND)
                                }
                                1007 -> {
                                    emitter?.onError(0, "登陆过期", ErrorType.BACKEND)
                                }
                                else -> {
                                    emitter?.onError(0, e.message ?: "Unknown Error", ErrorType.BACKEND)
                                }
                            }
                        }
                    }
                    is SocketTimeoutException -> {
                        emitter?.onEvent(StatusEvent.FAILURE)
                        emitter?.onError(0, "", ErrorType.TIMEOUT)
                        withContext(Dispatchers.Main) {
                           // todo 网络错误提醒

                        }
                    }
                    is IOException -> {
                        emitter?.onEvent(StatusEvent.FAILURE)
                        emitter?.onError(0, "", ErrorType.NETWORK)
                        withContext(Dispatchers.Main) {
                           // todo 网络错误提醒
                        }
                    }
                    else -> {
                        emitter?.onEvent(StatusEvent.ERROR)
                        emitter?.onError(0, "", ErrorType.UNKNOWN)
                    }
                }
            }
            null
        }
    }
}

