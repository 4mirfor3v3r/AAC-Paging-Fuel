package com.acemirr.cleanarchitecture.data.datasource.list

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalListModel

interface ListDataSource {
    interface Remote {
        fun getListPlaces(context: Context): ResState<List<ListPlaceModel>>?
    }

    interface Local : Remote {
        fun saveListPlaces(list: List<LocalListModel>?)
    }
}