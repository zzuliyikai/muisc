package com.hanshow.myapplication.flow

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hanshow.myapplication.databinding.ActivityFlowBinding

class FlowActivity : AppCompatActivity() {

    private val mBinding: ActivityFlowBinding by lazy {
        ActivityFlowBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(mBinding.root)







    }
}