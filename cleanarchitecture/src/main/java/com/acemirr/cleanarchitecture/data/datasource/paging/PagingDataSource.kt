package com.acemirr.cleanarchitecture.data.datasource.paging

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.PagingListsModel
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalNewsModel

interface PagingDataSource {
    interface Remote {
        fun getPagingLists(context: Context, param: List<Pair<String, Any>>): ResState<PagingListsModel>?
    }

    interface Local : Remote {
        fun savePagingLists(list: List<LocalNewsModel>)
    }
}