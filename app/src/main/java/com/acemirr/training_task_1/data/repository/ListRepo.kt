package com.acemirr.training_task_1.data.repository

import android.content.Context
import com.acemirr.training_task_1.data.model.ListModel
import com.acemirr.training_task_1.data.network.Endpoint
import com.acemirr.training_task_1.data.network.Network
import com.github.kittinunf.fuel.Fuel
import kotlinx.coroutines.CoroutineScope

class ListRepo(coroutineScope: CoroutineScope) {
    private val network = Network(coroutineScope)
    fun getListPlace(context: Context, onSuccess: (MutableList<ListModel>?) -> Unit, onFinally: (Boolean) -> Unit) {
        network.api<MutableList<ListModel>>(context,{
            Fuel.get(Endpoint.LIST_MALANG_BATU)
        },{
            onSuccess(it)
        },{
            onFinally(it)
        })
    }
}