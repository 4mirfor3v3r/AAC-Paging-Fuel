package com.acemirr.training_task_1.ui.menu.grid.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class CustomGridDetailViewModelFactory(val application: Application): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DetailGridViewModel(
            application
        ) as T
    }

}