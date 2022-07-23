package com.bkarakoca.exampleapp.scene.main

import android.os.Bundle
import androidx.activity.viewModels
import com.bkarakoca.exampleapp.R
import com.bkarakoca.exampleapp.base.BaseActivity
import com.bkarakoca.exampleapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ACMain : BaseActivity<ActivityMainBinding, ACMainVM>() {

    override val layoutId get() = R.layout.activity_main

    override val viewModel: ACMainVM by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binder.lifecycleOwner = this
    }
}
