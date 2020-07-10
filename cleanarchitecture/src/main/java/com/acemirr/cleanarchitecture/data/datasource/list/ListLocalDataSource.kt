package com.acemirr.cleanarchitecture.data.datasource.list

import android.content.Context
import com.acemirr.cleanarchitecture.data.exception.DataNotAvailableException
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote
import com.acemirr.cleanarchitecture.data.source.local.dao.ListDao
import com.acemirr.cleanarchitecture.data.utils.DiskExecutor
import com.acemirr.cleanarchitecture.data.utils.Mapper
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.acemirr.cleanarchitecture.domain.model.LocalListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListLocalDataSource @Inject constructor(private val executor: DiskExecutor, private val scope: CoroutineScope, private val dao: ListDao) : ListDataSource.Local {

    override fun getMovies(context: Context): ResState<List<ListPlaceRemote>> =
        runBlocking {
            val res = withContext(scope.coroutineContext) { dao.getAllList() }
            return@runBlocking if (res.isNotEmpty()) {
                ResState.Success(Mapper.toRemoteList(res))
            } else {
                ResState.Error<List<ListPlaceRemote>>(DataNotAvailableException())
            }
        }


    override fun saveMovies(movies: List<LocalListModel>) {
        executor.execute {
            dao.insertAllList(movies.toMutableList())
        }
    }

}