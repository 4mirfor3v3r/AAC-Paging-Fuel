package com.acemirr.cleanarchitecture.presenter.menu.grid.adapter

import android.view.View
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel

interface GridRVAction {
    fun onContainerClickListener(v: View, data: GridGalleryModel, position: Int)
}