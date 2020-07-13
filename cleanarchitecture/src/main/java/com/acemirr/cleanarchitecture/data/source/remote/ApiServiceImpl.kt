package com.acemirr.cleanarchitecture.data.source.remote

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel
import com.acemirr.cleanarchitecture.data.model.PagingListsModel
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.github.kittinunf.fuel.Fuel

class ApiServiceImpl : ApiService {
    val network = Network()

    override suspend fun getListPlace(context: Context): ResState<List<ListPlaceModel>> {
        return network.api(context) { Fuel.get(Endpoint.LIST_MALANG_BATU) }
    }

    override suspend fun getGridPlace(context: Context): ResState<List<GridGalleryModel>> {
        return network.api(context) { Fuel.get(Endpoint.GRID_GALLERY) }
    }

    override suspend fun getPaging(context: Context, param: List<Pair<String, Any>>): ResState<PagingListsModel> {
        return network.api(context) { Fuel.get(Endpoint.PAGING_BASE_URL, param) }
    }
}