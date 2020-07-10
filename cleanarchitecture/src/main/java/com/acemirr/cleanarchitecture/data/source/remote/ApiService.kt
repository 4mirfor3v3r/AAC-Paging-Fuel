package com.acemirr.cleanarchitecture.data.source.remote

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.GridModel
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote
import com.acemirr.cleanarchitecture.data.model.PagingResponse
import com.acemirr.cleanarchitecture.data.utils.ResState

interface ApiService {
    suspend fun getListPlace(context: Context): ResState<List<ListPlaceRemote>>

    suspend fun getGridPlace(context: Context): ResState<List<GridModel>>

    fun getPaging(
        context: Context,
        param: List<Pair<String, Any>>,
        onSuccess: (PagingResponse?) -> Unit,
        onFinnaly: (Boolean) -> Unit
    )
}