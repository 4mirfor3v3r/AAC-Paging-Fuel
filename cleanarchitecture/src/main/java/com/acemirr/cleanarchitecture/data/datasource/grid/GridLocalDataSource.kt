package com.acemirr.cleanarchitecture.data.datasource.grid

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.data.source.local.dao.GridDao
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalGridModel
import com.acemirr.cleanarchitecture.data.utils.DataNotAvailableException
import com.acemirr.cleanarchitecture.data.utils.DiskExecutor
import com.acemirr.cleanarchitecture.data.utils.Mapper
import com.acemirr.cleanarchitecture.data.utils.ResState
import kotlinx.coroutines.*
import javax.inject.Inject

class GridLocalDataSource @Inject constructor(private val executor: DiskExecutor, private val scope: CoroutineScope, private val dao: GridDao) : GridDataSource.Local {

    override fun getGridGallery(context: Context): ResState<List<GridGalleryModel>> {
        return runBlocking {
            delay(1000)
            val defRes = async {
                val res = withContext(scope.coroutineContext) { dao.getAllGrid() }
                return@async if (res.isNotEmpty()) {
                    ResState.Success(Mapper.toRemoteGrid(res))
                } else {
                    ResState.Error<List<GridGalleryModel>>(DataNotAvailableException())
                }
            }
            return@runBlocking defRes.await()
        }
    }


    override fun saveGridGallery(list: List<LocalGridModel>) {
        executor.execute {
            dao.insertAllGrid(list.toMutableList())
        }
    }

}