package com.acemirr.cleanarchitecture.presenter.menu.grid.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acemirr.cleanarchitecture.domain.usecase.GridUseCase
import com.acemirr.cleanarchitecture.presenter.menu.grid.model.GridModel

class DetailGridViewModel(private val useCase: GridUseCase) : ViewModel() {

//    private val repository = GridRepo(viewModelScope)

    var liveDataListGallery: MutableLiveData<MutableList<GridModel>> = MutableLiveData()
    private var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getListGallery(context: Context) {
        isLoading.postValue(true)
        useCase.getGridGallery(context, {
            liveDataListGallery.postValue(it)
        }, {
            isLoading.postValue(false)
        })
    }
}
