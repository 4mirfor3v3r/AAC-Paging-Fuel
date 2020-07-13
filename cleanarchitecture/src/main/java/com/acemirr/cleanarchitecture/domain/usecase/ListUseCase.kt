package com.acemirr.cleanarchitecture.domain.usecase

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel
import com.acemirr.cleanarchitecture.domain.repository.ListRepository
import javax.inject.Inject

class ListUseCase @Inject constructor(private val repository: ListRepository) {
    fun getListPlace(context: Context, onSuccess:(MutableList<ListPlaceModel>?) ->Unit, onFinnaly:(Boolean) -> Unit){
        repository.getList(context,{
            onSuccess(it)
        },{
            onFinnaly(it)
        })
    }
}