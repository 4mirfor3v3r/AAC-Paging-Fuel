package com.acemirr.cleanarchitecture.data.datasource.list

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.acemirr.cleanarchitecture.domain.model.LocalListModel

interface ListDataSource {
    interface Remote {
        fun getMovies(context: Context): ResState<List<ListPlaceRemote>>
    }

    interface Local : Remote {
        fun saveMovies(movies: List<LocalListModel>)
    }
}