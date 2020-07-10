package com.acemirr.cleanarchitecture.data.datasource.grid

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.GridModel
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.acemirr.cleanarchitecture.domain.model.LocalGridModel

interface GridDataSource {
    interface Remote {
        fun getMovies(context: Context): ResState<List<GridModel>>
    }

    interface Local : Remote {
        fun saveMovies(movies: List<LocalGridModel>)
    }
}