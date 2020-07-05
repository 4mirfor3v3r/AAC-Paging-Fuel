package com.acemirr.cleanarchitecture.data.repository

import com.acemirr.cleanarchitecture.data.model.ListPlaceLocal
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote

interface ListDataSource {

    interface Remote {
        suspend fun getListPlace(): MutableList<ListPlaceRemote>
    }

    interface Local : Remote {
        fun saveListPlace(movies: MutableList<ListPlaceLocal>)
    }
}
