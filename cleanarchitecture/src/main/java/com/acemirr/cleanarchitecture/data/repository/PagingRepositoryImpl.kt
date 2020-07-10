package com.acemirr.cleanarchitecture.data.repository

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.News
import com.acemirr.cleanarchitecture.data.source.remote.ApiServiceImpl
import com.acemirr.cleanarchitecture.domain.repository.PagingRepository
import com.acemirr.cleanarchitecture.external.logDebug

class PagingRepositoryImpl(private val apiServiceImpl: ApiServiceImpl): PagingRepository {
    override fun getPaging(context: Context,
                           param:List<Pair<String,Any>>, onSuccess: (List<News>?) -> Unit,onFinnaly:(Boolean) ->Unit){
        apiServiceImpl.getPaging(context,param,{
                    if (it != null) {
                        onSuccess(it.news)
                    }else
                        logDebug("Error Getting Data NULL")
        },{
            onFinnaly(it)
        })
    }
}