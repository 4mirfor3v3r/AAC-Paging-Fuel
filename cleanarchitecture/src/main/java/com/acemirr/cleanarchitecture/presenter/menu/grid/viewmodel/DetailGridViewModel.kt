package com.acemirr.cleanarchitecture.presenter.menu.grid.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.domain.usecase.GridUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailGridViewModel @Inject constructor(private val useCase: GridUseCase) : ViewModel() {

    var liveDataListGallery: MutableLiveData<MutableList<GridGalleryModel>> = MutableLiveData()
    private var isLoading: MutableLiveData<Boolean> = MutableLiveData()

    fun getListGallery(context: Context) {
        isLoading.postValue(true)
        viewModelScope.launch {
            useCase.getGridGallery(context, {
                liveDataListGallery.postValue(it)
            }, {
                isLoading.postValue(false)
            })
        }
    }
}
