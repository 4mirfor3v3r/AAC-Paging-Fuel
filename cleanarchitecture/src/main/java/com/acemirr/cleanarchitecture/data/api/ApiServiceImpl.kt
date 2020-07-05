package com.acemirr.cleanarchitecture.data.api

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote
import com.acemirr.cleanarchitecture.data.model.PagingResponse
import com.acemirr.cleanarchitecture.presenter.menu.grid.model.GridModel
import com.github.kittinunf.fuel.Fuel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ApiServiceImpl(private val coroutineScope: CoroutineScope) : ApiService {
    val network = Network(coroutineScope)

    override fun getListPlace(
        context: Context,
        onSuccess: (MutableList<ListPlaceRemote>) -> Unit,
        onFinnaly: (Boolean) -> Unit
    ){
        coroutineScope.launch {
            network.api<MutableList<ListPlaceRemote>>(context,
                { Fuel.get(Endpoint.LIST_MALANG_BATU) },
                {
                            onSuccess(it)
                },
                {
                    onFinnaly(it)
                })
        }
    }

    override fun getGridPlace(
        context: Context,
        onSuccess: (MutableList<GridModel>) -> Unit,
        onFinnaly: (Boolean) -> Unit
    ) {
        coroutineScope.launch {
            network.api<MutableList<GridModel>>(context,
                { Fuel.get(Endpoint.GRID_GALLERY) },
                {
                            onSuccess(it)
                },
                {
                    onFinnaly(it)
                })
        }
    }

    override fun getPaging(
        context: Context,
        param:List<Pair<String,Any>>,
        onSuccess: (PagingResponse) -> Unit,
        onFinnaly: (Boolean) -> Unit
    ) {
            network.api<PagingResponse>(context,{
                Fuel.get(Endpoint.PAGING_BASE_URL,param)
            },{
                onSuccess(it)
            },{
                onFinnaly(it)
            })
    }
}