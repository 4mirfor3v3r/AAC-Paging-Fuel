package com.acemirr.cleanarchitecture.presenter.menu.list.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel
import com.acemirr.cleanarchitecture.domain.usecase.ListUseCase
import com.acemirr.cleanarchitecture.external.logDebug
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class ListViewModel @Inject constructor(private val usecase: ListUseCase) : ViewModel() {

    var liveDataList: MutableLiveData<MutableList<ListPlaceModel>> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getList(context: Context){
        isLoading.postValue(true)
        viewModelScope.launch(Dispatchers.IO) {
            logDebug("ISLOADING ONE ${isLoading.value}")
            usecase.getListPlace(context, {
                liveDataList.postValue(it)
                logDebug("ISLOADING TWO ${isLoading.value}")
            }, {
                isLoading.postValue(!it)
                logDebug("ISLOADING THIRD ${it}")
            })
        }
    }
}
