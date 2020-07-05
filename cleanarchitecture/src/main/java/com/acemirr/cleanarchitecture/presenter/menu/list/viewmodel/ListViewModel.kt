package com.acemirr.cleanarchitecture.presenter.menu.list.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote
import com.acemirr.cleanarchitecture.domain.usecase.ListUseCase

class ListViewModel(private val usecase: ListUseCase) : ViewModel() {

    var liveDataList: MutableLiveData<MutableList<ListPlaceRemote>> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getList(context: Context){
        isLoading.postValue(true)
        usecase.getListPlace(context,{
            liveDataList.postValue(it)
        },{
            isLoading.postValue(!it)
        })
    }
}
