package com.acemirr.cleanarchitecture.data.repository

import android.content.Context
import com.acemirr.cleanarchitecture.data.datasource.grid.GridDataSource
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.data.utils.DataNotAvailableException
import com.acemirr.cleanarchitecture.data.utils.Mapper
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.acemirr.cleanarchitecture.domain.repository.GridRepository
import com.acemirr.cleanarchitecture.external.logDebug
import javax.inject.Inject

class GridRepositoryImpl @Inject constructor(private val local : GridDataSource.Local, private val remote : GridDataSource.Remote) : GridRepository {
    override fun getGrid(context: Context, onSuccess: (MutableList<GridGalleryModel>?) -> Unit, onFinnaly: (Boolean) -> Unit) {
        onSuccess(getGridLocalDataSource(context){
            onFinnaly(it)
        })
    }
    private fun getGridLocalDataSource(context: Context, onFinnaly: (Boolean) -> Unit): MutableList<GridGalleryModel>{
        return when(val result = local.getGridGallery(context)) {
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
        }
    }

    private fun getListRemoteDataSource(context: Context, onFinnaly: (Boolean) -> Unit): List<GridGalleryModel> {
        val list = remote.getGridGallery(context)
        logDebug("Getting response from Repository")
        return try {
            if (list is ResState.Success) {
                local.saveGridGallery(Mapper.toLocalGrid(list.data.toMutableList()))
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