package com.acemirr.cleanarchitecture.data.datasource.paging

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.PagingListsModel
import com.acemirr.cleanarchitecture.data.source.remote.ApiServiceImpl
import com.acemirr.cleanarchitecture.data.utils.ResState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PagingRemoteDataSource @Inject constructor(private val scope:CoroutineScope, private val apiService: ApiServiceImpl) : PagingDataSource.Remote {

    override fun getPagingLists(context: Context, param: List<Pair<String, Any>>): ResState<PagingListsModel> = runBlocking {
        return@runBlocking withContext(scope.coroutineContext){apiService.getPaging(context,param)}
    }
}