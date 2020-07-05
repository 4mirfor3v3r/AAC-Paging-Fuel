package com.acemirr.cleanarchitecture.data.api

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote
import com.acemirr.cleanarchitecture.data.model.PagingResponse
import com.acemirr.cleanarchitecture.presenter.menu.grid.model.GridModel

interface ApiService {
    fun getListPlace(
        context: Context,
        onSuccess: (MutableList<ListPlaceRemote>) -> Unit,
        onFinnaly: (Boolean) -> Unit
    )

    fun getGridPlace(
        context: Context,
        onSuccess: (MutableList<GridModel>) -> Unit,
        onFinnaly: (Boolean) -> Unit
    )

    fun getPaging(
        context: Context,
        param:List<Pair<String,Any>>,
        onSuccess: (PagingResponse) -> Unit,
        onFinnaly: (Boolean) -> Unit
    )
}