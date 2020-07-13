package com.acemirr.cleanarchitecture.data.datasource.list

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel
import com.acemirr.cleanarchitecture.data.source.remote.ApiServiceImpl
import com.acemirr.cleanarchitecture.data.utils.ResState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListRemoteDataSource @Inject constructor(
    private val scope: CoroutineScope,
    private val apiService: ApiServiceImpl
) : ListDataSource.Remote {

    override fun getListPlaces(context: Context): ResState<List<ListPlaceModel>> = runBlocking {
        return@runBlocking withContext(scope.coroutineContext) { apiService.getListPlace(context) }
    }
}