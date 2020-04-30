package com.acemirr.training_task_1.data.repository

import android.content.Context
import com.acemirr.training_task_1.data.model.Response
import com.acemirr.training_task_1.data.network.Endpoint
import com.acemirr.training_task_1.data.network.Network
import com.github.kittinunf.fuel.Fuel
import kotlinx.coroutines.CoroutineScope

class PagingRepo(val coroutineScope: CoroutineScope) {
    val network = Network(coroutineScope)

    fun getNewsPaging(context: Context, param:List<Pair<String,Any>>, onSuccess: (Response?) -> Unit, onFinally: (Boolean) -> Unit) {
        network.api<Response>(context,{
            Fuel.get(Endpoint.PAGING_BASE_URL,param)
        },{
            onSuccess(it)
        },{
            onFinally(it)
        })
    }
}