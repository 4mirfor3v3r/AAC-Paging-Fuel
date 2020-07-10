package com.acemirr.cleanarchitecture.presenter.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

abstract class BaseFragment<VM:ViewModel,BIND:ViewDataBinding>(@LayoutRes private val resId:Int):DaggerFragment() {

    protected lateinit var binding:BIND
    protected lateinit var viewModel:VM

    @Inject
    lateinit var daggerViewModelFactory: DaggersViewModelFactory

    protected abstract fun createViewModel():VM

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater,resId,container,false)
        binding.lifecycleOwner = this
        return binding.root

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel = createViewModel()
    }
}