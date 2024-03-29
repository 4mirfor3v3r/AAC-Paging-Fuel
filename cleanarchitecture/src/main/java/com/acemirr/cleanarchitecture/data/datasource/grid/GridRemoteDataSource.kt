package com.acemirr.cleanarchitecture.data.datasource.grid

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.data.source.remote.ApiServiceImpl
import com.acemirr.cleanarchitecture.data.utils.ResState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GridRemoteDataSource @Inject constructor(private val scope:CoroutineScope, private val apiService: ApiServiceImpl) : GridDataSource.Remote {

    override fun getGridGallery(context: Context): ResState<List<GridGalleryModel>> = runBlocking{
        return@runBlocking withContext(scope.coroutineContext) {apiService.getGridPlace(context)}
    }

}