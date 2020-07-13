package com.acemirr.cleanarchitecture.presenter.menu.list.adapter

import android.view.View
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel

interface ListRVAction {
    fun onContainerClickListener(v: View, data:ListPlaceModel)
}