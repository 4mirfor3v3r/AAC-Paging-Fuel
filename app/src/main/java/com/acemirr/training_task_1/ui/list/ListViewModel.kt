package com.acemirr.training_task_1.ui.list

import android.app.Application
import android.content.Context
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.acemirr.training_task_1.data.model.PlaceList
import com.acemirr.training_task_1.data.repository.ListRepo

class ListViewModel(application: Application) : AndroidViewModel(application) {
    private val repository = ListRepo(viewModelScope)

    var liveDataList: MutableLiveData<MutableList<PlaceList>> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getList(context: Context){
        isLoading.postValue(true)
        repository.getListPlace(context,{
            liveDataList.postValue(it)
        },{
            if (it)
                isLoading.postValue(false)
        })
    }
}
