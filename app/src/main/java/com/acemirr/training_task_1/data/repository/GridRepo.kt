package com.acemirr.training_task_1.data.repository

import android.content.Context
import com.acemirr.training_task_1.data.model.GridModel
import com.acemirr.training_task_1.data.network.Endpoint
import com.acemirr.training_task_1.data.network.Network
import com.github.kittinunf.fuel.Fuel
import kotlinx.coroutines.CoroutineScope

class GridRepo(coroutineScope: CoroutineScope) {
    val network = Network(coroutineScope)

    fun getGridPlace(context: Context, onSuccess: (MutableList<GridModel>?) -> Unit, onFinally: (Boolean) -> Unit) {
        network.api<MutableList<GridModel>>(context,{
            Fuel.get(Endpoint.GRID_GALLERY)
        },{
            onSuccess(it)
        },{
            onFinally(it)
        })
    }
}