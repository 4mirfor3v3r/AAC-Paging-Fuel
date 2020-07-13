package com.acemirr.cleanarchitecture.domain.usecase

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.domain.repository.GridRepository
import javax.inject.Inject

class GridUseCase @Inject constructor(private val repository: GridRepository) {
    fun getGridGallery(context: Context, onSuccess:(MutableList<GridGalleryModel>?) ->Unit, onFinnaly:(Boolean) -> Unit){
        repository.getGrid(context,{
            onSuccess(it)
        },{
            onFinnaly(it)
        })
    }
}