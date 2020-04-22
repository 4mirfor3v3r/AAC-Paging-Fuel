package com.acemirr.training_task_1.data.network

import android.content.Context
import com.acemirr.training_task_1.utils.Constants
import com.acemirr.training_task_1.utils.logDebug
import com.acemirr.training_task_1.utils.logError
import com.acemirr.training_task_1.utils.showToast
import com.github.kittinunf.fuel.Fuel
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.isClientError
import com.github.kittinunf.fuel.core.isSuccessful
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch

class Network(private val coroutineScope: CoroutineScope) {

    fun <T> request(context: Context, response: suspend() -> Response, onSuccess:(T?) -> Unit, onFinally:(Boolean) -> Unit) {
        coroutineScope.launch {
            try {
                val result = response()
                if (result.isSuccessful) {
                    logDebug("Network # isSuccessful")
                    logDebug("Network # url API : ${result.url}")
                    onSuccess(result.body() as T?)
                } else {
                    logError("Network # UnSuccessFul")
                    logError("Network # url API : ${result.url}")
                    logError("Network # code : ${result.statusCode}")
                    logError("Network # body : ${result.responseMessage}")
                    context.showToast("UnSuccessFul # code : ${result.statusCode}")
                }
            } catch (throwable: Throwable) {
                if (throwable is CancellationException) {
                    // coroutines has canceled
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