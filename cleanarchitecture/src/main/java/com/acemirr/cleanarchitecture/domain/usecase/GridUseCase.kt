package com.acemirr.cleanarchitecture.domain.usecase

import android.content.Context
import com.acemirr.cleanarchitecture.domain.repository.GridRepository
import com.acemirr.cleanarchitecture.presenter.menu.grid.model.GridModel

class GridUseCase(private val repository: GridRepository) {
    fun getGridGallery(context: Context,onSuccess:(MutableList<GridModel>?) ->Unit,onFinnaly:(Boolean) -> Unit){
        repository.getGrid(context,{
            onSuccess(it)
        },{
            onFinnaly(it)
        })
    }
}