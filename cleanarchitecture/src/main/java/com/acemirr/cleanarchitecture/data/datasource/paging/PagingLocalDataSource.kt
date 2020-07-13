package com.acemirr.cleanarchitecture.data.datasource.paging

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.PagingListsModel
import com.acemirr.cleanarchitecture.data.source.local.dao.PagingDao
import com.acemirr.cleanarchitecture.data.utils.DataNotAvailableException
import com.acemirr.cleanarchitecture.data.utils.DiskExecutor
import com.acemirr.cleanarchitecture.data.utils.Mapper
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalNewsModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PagingLocalDataSource @Inject constructor(
    private val executor: DiskExecutor,
    private val scope: CoroutineScope,
    private val dao: PagingDao
) : PagingDataSource.Local {

    override fun getPagingLists(context: Context, param: List<Pair<String, Any>>): ResState<PagingListsModel>? {
        val page = param.first().second as Int
        val pageSize = param.last().second as Int
        return runBlocking {
            val maxPage = (dao.getPagingCount() ?: 0) / page
            val res = async {
                if (page < maxPage) {
                    val ret = withContext(scope.coroutineContext) { dao.getPagedPaging(page, pageSize) }
                    return@async if (ret.isNotEmpty()) {
                        ResState.Success(PagingListsModel(Mapper.toPagingNewsRemote(ret)))
                    } else
                        ResState.Error<PagingListsModel>(DataNotAvailableException())
                } else
                    ResState.Error<PagingListsModel>(DataNotAvailableException())
            }
            return@runBlocking res.await()
        }
    }

    override fun savePagingLists(list: List<LocalNewsModel>) {
        executor.execute {
            dao.insertAllPaging(list.toMutableList())
        }
    }

}