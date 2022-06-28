package com.hanshow.myapplication.api

import androidx.lifecycle.MutableLiveData
import com.hanshow.myapplication.base.repository.BaseRemoteRepository
import com.hanshow.myapplication.bean.Music

/**
 * Created by yikai on 2022/6/1.
 * Describe:
 */
object DataRepository : BaseRemoteRepository() {
    private val marsApi = RetrofitProvider.marsApi

    suspend fun getMusicList(
        liveData: MutableLiveData<Music>,
        remoteEventEmitter: RemoteEventEmitter,
    ) {
        val response = safeApiCall(remoteEventEmitter) {
            marsApi.getMusicById(123)
        }
        if (response != null) {
            liveData.postValue(response.data)
        }
    }

    suspend fun getMusicById(
        liveData: MutableLiveData<Music>,
        remoteEventEmitter: RemoteEventEmitter,
    ) {
        val response = safeApiCall(remoteEventEmitter) {
            marsApi.getMusicById(123)
        }
        if (response != null) {
            liveData.postValue(response.data)
        }
    }

}