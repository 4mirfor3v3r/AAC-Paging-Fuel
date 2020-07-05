package com.acemirr.cleanarchitecture.presenter.menu.list.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote

class CustomListDetailViewModelFactory(private val modelModel: ListPlaceRemote, val application: Application): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DetailListViewModel(
            modelModel,
            application
        ) as T
    }

}