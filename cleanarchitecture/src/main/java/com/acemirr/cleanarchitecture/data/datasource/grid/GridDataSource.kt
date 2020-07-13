package com.acemirr.cleanarchitecture.data.datasource.grid

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalGridModel

interface GridDataSource {
    interface Remote {
        fun getGridGallery(context: Context): ResState<List<GridGalleryModel>>
    }

    interface Local : Remote {
        fun saveGridGallery(list: List<LocalGridModel>)
    }
}