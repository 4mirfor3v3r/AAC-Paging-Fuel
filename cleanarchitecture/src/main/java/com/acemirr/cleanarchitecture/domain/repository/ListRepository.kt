package com.acemirr.cleanarchitecture.domain.repository

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote

interface ListRepository {
    fun getList(context: Context, onSuccess: (MutableList<ListPlaceRemote>?) -> Unit, onFinnaly: (Boolean) -> Unit)
}