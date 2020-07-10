package com.acemirr.cleanarchitecture.data.source.remote

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.GridModel
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote
import com.acemirr.cleanarchitecture.data.model.PagingResponse
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.github.kittinunf.fuel.Fuel
import kotlinx.coroutines.CoroutineScope

class ApiServiceImpl(private val coroutineScope: CoroutineScope) : ApiService {
    val network = Network(coroutineScope)
    val fakeNetwork = FakeNetwork()

    override suspend fun getListPlace(context: Context): ResState<List<ListPlaceRemote>> {
        return fakeNetwork.api(context) { Fuel.get(Endpoint.LIST_MALANG_BATU) }
    }

    override suspend fun getGridPlace(context: Context): ResState<List<GridModel>> {
        return fakeNetwork.api(context) { Fuel.get(Endpoint.GRID_GALLERY) }
    }

    override fun getPaging(
        context: Context,
        param: List<Pair<String, Any>>,
        onSuccess: (PagingResponse?) -> Unit,
        onFinnaly: (Boolean) -> Unit
    ) {
        network.api<PagingResponse>(context, {
            Fuel.get(Endpoint.PAGING_BASE_URL, param)
        }, {
            onSuccess(it)
        }, {
            onFinnaly(it)
        })
    }
}