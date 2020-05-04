package com.acemirr.training_task_1.data.network

import android.content.Context
import com.acemirr.training_task_1.utils.logDebug
import com.acemirr.training_task_1.utils.logError
import com.acemirr.training_task_1.utils.showToast
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import com.github.kittinunf.fuel.gson.gsonDeserializer
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Network(val coroutineScope: CoroutineScope) {

    inline fun <reified T : Any> api(
        context: Context,
        noinline request: suspend () -> Request,
        noinline onSuccess: (T) -> Unit,
        noinline onFinally: (Boolean) -> Unit
    ) {
        coroutineScope.launch {
            try {
                request.invoke().awaitObjectResult(object : ResponseDeserializable<T> {
                    override fun deserialize(response: Response): T {
                        return gsonDeserializer<T>().deserialize(response)
                    }
                }).fold({
                    onSuccess(it)
                }, {
                    logError("Network # UnSuccessFul")
                    logError("Network # url API : ${it.response.url}")
                    logError("Network # code : ${it.response.statusCode}")
                    context.showToast("UnSuccessFul # code : ${it.message}")
                })
            } catch (throwable: Throwable) {
                if (throwable is CancellationException) {
                    logError("Network # Throwable ==> job canceled")
                } else {
                    logError("Network # Throwable")
                    context.showToast("Throwable ${throwable.message})}")
                    throwable.printStackTrace()
                }
            } finally {
                logDebug("Network # finally")
                onFinally(true)
            }
        }
    }
}