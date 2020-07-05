package com.acemirr.cleanarchitecture.domain.usecase

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.News
import com.acemirr.cleanarchitecture.domain.repository.PagingRepository

class PagingUseCase(private val repository: PagingRepository) {
    fun getPaging(context: Context,
                  param:List<Pair<String,Any>>,onSuccess:(List<News>) ->Unit,onFinnaly:(Boolean) -> Unit){
        repository.getPaging(context,param,{
            if (it != null)
                onSuccess(it)
        },{
            onFinnaly(it)
        })
    }
}