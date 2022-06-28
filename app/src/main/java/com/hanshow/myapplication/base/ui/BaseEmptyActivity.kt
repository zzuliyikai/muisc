package com.hanshow.myapplication.base.ui

import androidx.databinding.ViewDataBinding
import com.hanshow.myapplication.base.viewmodel.EmptyViewModel

abstract class BaseEmptyActivity<VDB : ViewDataBinding> : BaseActivity<EmptyViewModel, VDB>() {

    override fun initViewModel(): EmptyViewModel {
        return EmptyViewModel()
    }

}