package com.acemirr.cleanarchitecture.domain.usecase

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.PagingNewsModel
import com.acemirr.cleanarchitecture.domain.repository.PagingRepository
import javax.inject.Inject

class PagingUseCase @Inject constructor(private val repository: PagingRepository) {
    fun getPaging(context: Context,
                  param:List<Pair<String,Any>>, onSuccess:(List<PagingNewsModel>) ->Unit, onFinnaly:(Boolean) -> Unit){
        repository.getPaging(context,param,{
            if (it != null)
                onSuccess(it)
        },{
            onFinnaly(it)
        })
    }
}