package com.acemirr.cleanarchitecture.domain.repository

import android.content.Context
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel

interface GridRepository {
    fun getGrid(context: Context, onSuccess: (MutableList<GridGalleryModel>?) -> Unit, onFinnaly: (Boolean) -> Unit)
}