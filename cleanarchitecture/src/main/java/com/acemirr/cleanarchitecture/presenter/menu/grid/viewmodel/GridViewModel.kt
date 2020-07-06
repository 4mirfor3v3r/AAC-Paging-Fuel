package com.acemirr.cleanarchitecture.presenter.menu.grid.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acemirr.cleanarchitecture.domain.usecase.GridUseCase
import com.acemirr.cleanarchitecture.data.model.GridModel

class GridViewModel(private val useCase: GridUseCase) : ViewModel() {

    var liveDataListGallery: MutableLiveData<MutableList<GridModel>> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getListGallery(context: Context) {
        isLoading.postValue(true)
        useCase.getGridGallery(context, {
            liveDataListGallery.postValue(it)
        }, {
            isLoading.postValue(false)
        })
    }

}
