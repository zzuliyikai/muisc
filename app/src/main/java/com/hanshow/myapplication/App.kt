package com.hanshow.myapplication

import android.app.Application
import androidx.lifecycle.ProcessLifecycleOwner

/**
 * Created by yikai on 2022/5/31.
 * Describe:
 */
class App : Application() {

    override fun onCreate() {
        super.onCreate()

        ProcessLifecycleOwner.get().lifecycle.addObserver(ApplicationLifecycleObserver())
    }
}