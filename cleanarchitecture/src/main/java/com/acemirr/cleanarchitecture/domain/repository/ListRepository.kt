package com.acemirr.cleanarchitecture.domain.repository

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel

interface ListRepository {
    fun getList(context: Context, onSuccess: (MutableList<ListPlaceModel>?) -> Unit, onFinnaly: (Boolean) -> Unit)
}