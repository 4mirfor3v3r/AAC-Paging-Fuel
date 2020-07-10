package com.acemirr.cleanarchitecture.data.datasource.list

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote
import com.acemirr.cleanarchitecture.data.source.remote.ApiServiceImpl
import com.acemirr.cleanarchitecture.data.utils.ResState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListRemoteDataSource @Inject constructor(private val scope:CoroutineScope, private val apiService: ApiServiceImpl) : ListDataSource.Remote {

    override fun getMovies( context: Context): ResState<List<ListPlaceRemote>> = runBlocking{
        return@runBlocking withContext(scope.coroutineContext) { apiService.getListPlace(context) }
    }

}