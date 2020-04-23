package com.acemirr.training_task_1.data.repository

import android.content.Context
import com.acemirr.training_task_1.data.model.ListModel
import com.acemirr.training_task_1.data.network.Endpoint
import com.acemirr.training_task_1.utils.showToast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.gson.responseObject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class ListRepo(val coroutineScope: CoroutineScope) {
    lateinit var requestList: Request

    fun getListPlace(context: Context, onSuccess: (MutableList<ListModel>?) -> Unit, onFinally: (Boolean) -> Unit) {
        coroutineScope.launch {
            try {
                requestList = Fuel.get(Endpoint.LIST_MALANG_BATU)
                    .responseObject<MutableList<ListModel>> { _, _, result ->
                        result.fold({
                            onSuccess(it)
                        }, {
                            context.showToast("Failed to Fetch Data\nCode :" + it.response.statusCode.toString() + "\nMessage : ${it.response.responseMessage}")
                        })
                    }
            } catch (e: CancellationException) {
                e.printStackTrace()
            } finally {
                onFinally(true)
            }
        }
    }
}