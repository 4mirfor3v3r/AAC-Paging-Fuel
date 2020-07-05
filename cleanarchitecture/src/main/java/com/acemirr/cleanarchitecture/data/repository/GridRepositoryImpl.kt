package com.acemirr.cleanarchitecture.data.repository

import android.content.Context
import com.acemirr.cleanarchitecture.data.api.ApiServiceImpl
import com.acemirr.cleanarchitecture.domain.repository.GridRepository
import com.acemirr.cleanarchitecture.external.logDebug
import com.acemirr.cleanarchitecture.presenter.menu.grid.model.GridModel

class GridRepositoryImpl(private val apiServiceImpl: ApiServiceImpl): GridRepository {

    override fun getGrid(
        context: Context,
        onSuccess: (MutableList<GridModel>?) -> Unit,
        onFinnaly: (Boolean) -> Unit
    ) {
        apiServiceImpl.getGridPlace(context,{
                    onSuccess(it)
                    logDebug("Error Getting Data")
        },{
            onFinnaly(it)
        })
    }

}