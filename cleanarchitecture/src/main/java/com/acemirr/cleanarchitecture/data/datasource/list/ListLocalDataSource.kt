package com.acemirr.cleanarchitecture.data.datasource.list

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel
import com.acemirr.cleanarchitecture.data.source.local.dao.ListDao
import com.acemirr.cleanarchitecture.data.utils.DataNotAvailableException
import com.acemirr.cleanarchitecture.data.utils.DiskExecutor
import com.acemirr.cleanarchitecture.data.utils.Mapper
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalListModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ListLocalDataSource @Inject constructor(
    private val executor: DiskExecutor,
    private val scope: CoroutineScope,
    private val dao: ListDao
) : ListDataSource.Local {

    override fun getListPlaces(context: Context): ResState<List<ListPlaceModel>>? {
        return runBlocking {
            val res = async {
                val ret = withContext(scope.coroutineContext) { dao.getAllList() }
                return@async if (ret.isNotEmpty()){
                    ResState.Success(Mapper.toRemoteList(ret))
                }
                else
                    ResState.Error<List<ListPlaceModel>>(DataNotAvailableException())
            }
            return@runBlocking res.await()
        }
    }

    override fun saveListPlaces(list: List<LocalListModel>?) {
        executor.execute {
            if (list != null) {
                dao.insertAllList(list.toMutableList())
            }
        }
    }

}