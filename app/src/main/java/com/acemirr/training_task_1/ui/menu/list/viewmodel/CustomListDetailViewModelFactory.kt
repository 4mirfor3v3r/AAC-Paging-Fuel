package com.acemirr.training_task_1.ui.menu.list.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acemirr.training_task_1.ui.menu.list.model.ListModel

class CustomListDetailViewModelFactory(private val modelModel: ListModel, val application: Application): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DetailListViewModel(
            modelModel,
            application
        ) as T
    }

}