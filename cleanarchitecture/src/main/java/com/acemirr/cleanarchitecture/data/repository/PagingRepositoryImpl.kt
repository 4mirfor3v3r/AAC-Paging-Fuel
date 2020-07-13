package com.acemirr.cleanarchitecture.data.repository

import android.content.Context
import com.acemirr.cleanarchitecture.data.datasource.paging.PagingDataSource
import com.acemirr.cleanarchitecture.data.model.PagingNewsModel
import com.acemirr.cleanarchitecture.data.utils.DataNotAvailableException
import com.acemirr.cleanarchitecture.data.utils.Mapper
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.acemirr.cleanarchitecture.domain.repository.PagingRepository
import com.acemirr.cleanarchitecture.external.logDebug

class PagingRepositoryImpl(private val local: PagingDataSource.Local, private val remote:PagingDataSource.Remote) : PagingRepository {
    override fun getPaging(
        context: Context,
        param: List<Pair<String, Any>>,
        onSuccess: (List<PagingNewsModel>?) -> Unit,
        onFinnaly: (Boolean) -> Unit
    ) {
        onSuccess(getListLocalDataSource(context,param){
            onFinnaly(it)
        })
    }

    private fun getListLocalDataSource(context: Context, param: List<Pair<String, Any>>, onFinnaly: (Boolean) -> Unit): List<PagingNewsModel> {
        return when (val result = local.getPagingLists(context,param)) {
            is ResState.Success -> {
                logDebug("Getting response from database")
                onFinnaly(true)
                result.data.pagingNewsModel
            }

            is ResState.Error -> {
                getListRemoteDataSource(context,param) {
                    onFinnaly(it)
                }.toMutableList()
            }
            else -> emptyList()
        }
    }

    private fun getListRemoteDataSource(context: Context, param: List<Pair<String, Any>>, onFinnaly: (Boolean) -> Unit): List<PagingNewsModel> {
        val list = remote.getPagingLists(context,param)
        logDebug("Getting response from Repository")
        return try {
            if (list is ResState.Success) {
                local.savePagingLists(Mapper.toPagingNewsLocal(list.data.pagingNewsModel))
                onFinnaly(true)
                list.data.pagingNewsModel
            } else {
                onFinnaly(true)
                throw DataNotAvailableException()
            }
        }catch (e: DataNotAvailableException){
            onFinnaly(true)
            emptyList()
        }
    }
}