package com.acemirr.cleanarchitecture.domain.repository

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.PagingNewsModel

interface PagingRepository {
    fun getPaging(context: Context, param:List<Pair<String,Any>>, onSuccess: (List<PagingNewsModel>?) -> Unit, onFinnaly: (Boolean) -> Unit)
}