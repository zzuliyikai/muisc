package com.hanshow.myapplication.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.hanshow.myapplication.api.DataRepository
import com.hanshow.myapplication.base.viewmodel.BaseViewModel
import com.hanshow.myapplication.bean.Music
import kotlinx.coroutines.launch

/**
 * Created by yikai on 2022/6/23.
 * Describe:
 */
class MainViewModel : BaseViewModel() {
    val musicLifeData = MutableLiveData<Music>()

    fun getMusicById() {
        viewModelScope.launch {
            DataRepository.getMusicById(musicLifeData, this@MainViewModel)
        }
    }


}