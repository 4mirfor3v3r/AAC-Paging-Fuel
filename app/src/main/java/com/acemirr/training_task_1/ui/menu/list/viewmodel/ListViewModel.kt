package com.acemirr.training_task_1.ui.menu.list.viewmodel

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.acemirr.training_task_1.data.repository.ListRepo
import com.acemirr.training_task_1.ui.menu.list.model.ListModel

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ListRepo(viewModelScope)

    var liveDataListModel: MutableLiveData<MutableList<ListModel>> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getList(context: Context){
        isLoading.postValue(true)
        repository.getListPlace(context,{
            liveDataListModel.postValue(it)
        },{
            if (it)
                isLoading.postValue(false)
        })
    }
}
