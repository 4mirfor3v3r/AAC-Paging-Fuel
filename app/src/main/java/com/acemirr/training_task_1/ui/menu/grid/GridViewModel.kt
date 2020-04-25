package com.acemirr.training_task_1.ui.menu.grid

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.acemirr.training_task_1.data.model.GridModel
import com.acemirr.training_task_1.data.repository.GridRepo

class GridViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = GridRepo(viewModelScope)

    var liveDataListGallery: MutableLiveData<MutableList<GridModel>> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getListGallery(context: Context) {
        isLoading.postValue(true)
        repository.getGridPlace(context, {
            liveDataListGallery.postValue(it)
        }, {
            isLoading.postValue(false)
        })
    }

}
