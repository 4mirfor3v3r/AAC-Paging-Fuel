package com.acemirr.cleanarchitecture.domain.usecase

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote
import com.acemirr.cleanarchitecture.domain.repository.ListRepository

class ListUseCase(private val listRepository: ListRepository) {
    fun getListPlace(context: Context,onSuccess:(MutableList<ListPlaceRemote>?) ->Unit,onFinnaly:(Boolean) -> Unit){
        listRepository.getList(context,{
            onSuccess(it)
        },{
            onFinnaly(it)
        })
    }
}