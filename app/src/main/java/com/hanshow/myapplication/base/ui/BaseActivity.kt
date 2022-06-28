package com.hanshow.myapplication.base.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel

abstract class BaseActivity<VMD : ViewModel, VDB : ViewDataBinding> : AppCompatActivity() {
    protected lateinit var viewModel: VMD
    protected lateinit var dataBinding: VDB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = initViewModel()
        val binding = DataBindingUtil.setContentView<VDB>(this, getLayoutId())
        binding.lifecycleOwner = this
        dataBinding = binding

        initData(savedInstanceState)
    }

    abstract fun getLayoutId(): Int

    abstract fun initViewModel(): VMD

    abstract fun initData(savedInstanceState: Bundle?)

}