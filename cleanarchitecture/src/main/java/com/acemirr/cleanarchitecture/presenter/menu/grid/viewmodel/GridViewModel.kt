package com.acemirr.cleanarchitecture.presenter.menu.grid.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.domain.usecase.GridUseCase
import javax.inject.Inject

class GridViewModel @Inject constructor(private val useCase: GridUseCase) : ViewModel() {

    var liveDataGallery: MutableLiveData<MutableList<GridGalleryModel>> = MutableLiveData()
    var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getListGallery(context: Context) {
        isLoading.postValue(true)
        useCase.getGridGallery(context, {
            liveDataGallery.postValue(it)
        }, {
            isLoading.postValue(false)
        })
    }

}
