package com.acemirr.cleanarchitecture.data.source.remote

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel
import com.acemirr.cleanarchitecture.data.model.PagingListsModel
import com.acemirr.cleanarchitecture.data.utils.ResState

interface ApiService {
    suspend fun getListPlace(context: Context): ResState<List<ListPlaceModel>>

    suspend fun getGridPlace(context: Context): ResState<List<GridGalleryModel>>

    suspend fun getPaging(context: Context, param: List<Pair<String, Any>>):ResState<PagingListsModel>
}