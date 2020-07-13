package com.acemirr.cleanarchitecture.presenter.menu.paging.adapter

import android.view.View
import com.acemirr.cleanarchitecture.data.model.PagingNewsModel

interface PagingRVAction {
    fun onContainerClick(v: View, data: PagingNewsModel)
}