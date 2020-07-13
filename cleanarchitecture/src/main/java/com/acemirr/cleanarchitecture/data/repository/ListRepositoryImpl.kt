package com.acemirr.cleanarchitecture.data.repository

import android.content.Context
import com.acemirr.cleanarchitecture.data.datasource.list.ListDataSource
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel
import com.acemirr.cleanarchitecture.data.utils.DataNotAvailableException
import com.acemirr.cleanarchitecture.data.utils.Mapper
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.acemirr.cleanarchitecture.domain.repository.ListRepository
import com.acemirr.cleanarchitecture.external.logDebug
import javax.inject.Inject

class ListRepositoryImpl @Inject constructor(private val local : ListDataSource.Local, private val remote : ListDataSource.Remote) : ListRepository {
    override fun getList(context: Context, onSuccess: (MutableList<ListPlaceModel>?) -> Unit, onFinnaly: (Boolean) -> Unit) {
        onSuccess(getListLocalDataSource(context) {
            onFinnaly(it)
        })
    }
    private fun getListLocalDataSource(context: Context, onFinnaly: (Boolean) -> Unit): MutableList<ListPlaceModel> {
        return when (val result = local.getListPlaces(context)) {
            is ResState.Success -> {
                logDebug("Getting response from database")
                onFinnaly(true)
                result.data.toMutableList()
            }

            is ResState.Error -> {
                getListRemoteDataSource(context) {
                    onFinnaly(it)
                }.toMutableList()
            }
            else -> emptyList<ListPlaceModel>().toMutableList()
        }
    }

    private fun getListRemoteDataSource(context: Context, onFinnaly: (Boolean) -> Unit): List<ListPlaceModel> {
        val list = remote.getListPlaces(context)
        logDebug("Getting response from Repository")
        return try {
            if (list is ResState.Success) {
                local.saveListPlaces(Mapper.toLocalList(list.data.toMutableList()))
                onFinnaly(true)
                list.data
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