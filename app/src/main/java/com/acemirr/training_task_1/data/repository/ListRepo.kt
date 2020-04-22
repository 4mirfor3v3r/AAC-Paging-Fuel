package com.acemirr.training_task_1.data.repository

import android.content.Context
import com.acemirr.training_task_1.data.model.PlaceList
import com.acemirr.training_task_1.data.network.Endpoint
import com.acemirr.training_task_1.utils.showToast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.awaitResponse
import com.github.kittinunf.fuel.gson.responseObject
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import java.lang.Exception

class ListRepo(val coroutineScope: CoroutineScope) {
    lateinit var requestList: Request

    //    fun getListPlace(context: Context, onSuccess: (MutableList<PlaceList>?) -> Unit, onFinally:(Boolean) -> Unit) {
//        network.request(context, {
//            restApi.getListPlace()
//        }, {
//            onSuccess(it)
//        }, {
//            onFinally(true)
//        })
//        requestList = Fuel.get(Endpoint.LIST_MALANG_BATU)
//    }
    fun getListPlace(context: Context, onSuccess: (MutableList<PlaceList>?) -> Unit, onFinally: (Boolean) -> Unit) {
        coroutineScope.launch {
            try {
                requestList = Fuel.get(Endpoint.LIST_MALANG_BATU)
                    .responseObject<MutableList<PlaceList>> { _, _, result ->
                        result.fold({
                            onSuccess(it)
                        }, {
                            context.showToast("Failed to Fetch Data\nCode :" + it.response.statusCode.toString() + "\nMessage : ${it.response.responseMessage}")
                        })
                    }
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                onFinally(true)
            }
        }
    }
}