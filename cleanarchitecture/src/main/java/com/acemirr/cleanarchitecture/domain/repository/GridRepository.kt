package com.acemirr.cleanarchitecture.domain.repository

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.GridModel

interface GridRepository {
    fun getGrid(context: Context, onSuccess: (MutableList<GridModel>?) -> Unit, onFinnaly: (Boolean) -> Unit)
}