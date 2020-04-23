package com.acemirr.training_task_1.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acemirr.training_task_1.data.model.GridModel
import com.acemirr.training_task_1.ui.detailGrid.DetailGridViewModel

class CustomGridDetailViewModelFactory(private val gridModel: GridModel, val application: Application): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DetailGridViewModel(gridModel, application) as T
    }

}