package com.acemirr.cleanarchitecture.data.datasource.grid

import android.content.Context
import com.acemirr.cleanarchitecture.data.exception.DataNotAvailableException
import com.acemirr.cleanarchitecture.data.model.GridModel
import com.acemirr.cleanarchitecture.data.source.local.dao.ListDao
import com.acemirr.cleanarchitecture.data.utils.DiskExecutor
import com.acemirr.cleanarchitecture.data.utils.Mapper
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.acemirr.cleanarchitecture.domain.model.LocalGridModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GridLocalDataSource @Inject constructor(private val executor: DiskExecutor, private val scope: CoroutineScope, private val dao: ListDao) : GridDataSource.Local {

    override fun getMovies(context: Context): ResState<List<GridModel>> =
        runBlocking {
            val res = withContext(scope.coroutineContext) { dao.getAllGrid() }
            return@runBlocking if (res.isNotEmpty()) {
                ResState.Success(Mapper.toRemoteGrid(res))
            } else {
                ResState.Error<List<GridModel>>(DataNotAvailableException())
            }
        }


    override fun saveMovies(movies: List<LocalGridModel>) {
        executor.execute {
            dao.insertAllGrid(movies.toMutableList())
        }
    }

}