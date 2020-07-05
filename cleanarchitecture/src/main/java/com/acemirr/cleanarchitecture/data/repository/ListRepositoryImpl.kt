package com.acemirr.cleanarchitecture.data.repository

import android.content.Context
import com.acemirr.cleanarchitecture.data.api.ApiServiceImpl
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote
import com.acemirr.cleanarchitecture.domain.repository.ListRepository
import com.acemirr.cleanarchitecture.external.logDebug

class ListRepositoryImpl(private val apiServiceImpl: ApiServiceImpl): ListRepository {
    override fun getList(context: Context, onSuccess: (MutableList<ListPlaceRemote>?) -> Unit,onFinnaly:(Boolean) ->Unit){
        apiServiceImpl.getListPlace(context,{
                    onSuccess(it)
                    logDebug("Error Getting Data")
        },{
            onFinnaly(it)
        })
    }

}