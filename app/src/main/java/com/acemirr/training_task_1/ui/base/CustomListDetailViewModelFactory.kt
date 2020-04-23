package com.acemirr.training_task_1.ui.base

import android.app.Application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acemirr.training_task_1.data.model.PlaceList
import com.acemirr.training_task_1.ui.detailList.DetailViewModel

class CustomListDetailViewModelFactory(private val placeModel: PlaceList, val application: Application): ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        @Suppress("UNCHECKED_CAST")
        return DetailViewModel(placeModel, application) as T
    }

}