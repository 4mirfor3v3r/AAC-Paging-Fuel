package com.acemirr.cleanarchitecture.domain.repository

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.News

interface PagingRepository {
    fun getPaging(context: Context,
                  param:List<Pair<String,Any>>, onSuccess: (List<News>?) -> Unit, onFinnaly: (Boolean) -> Unit)
}