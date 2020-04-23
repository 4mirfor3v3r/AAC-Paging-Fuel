package com.acemirr.training_task_1.data.repository

import android.content.Context
import com.acemirr.training_task_1.data.model.GridModel
import com.acemirr.training_task_1.data.network.Endpoint
import com.acemirr.training_task_1.utils.showToast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.gson.responseObject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class GridRepo(val coroutineScope: CoroutineScope) {
    lateinit var requestList: Request

    fun getGridPlace(context: Context, onSuccess: (MutableList<GridModel>?) -> Unit, onFinally: (Boolean) -> Unit) {
        coroutineScope.launch {
            try {
                requestList = Fuel.get(Endpoint.GRID_GALLERY)
                    .responseObject<MutableList<GridModel>> { _, _, result ->
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