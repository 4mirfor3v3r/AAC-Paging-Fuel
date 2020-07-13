package com.acemirr.cleanarchitecture.data.source.remote

import android.content.Context
import com.acemirr.cleanarchitecture.data.utils.DataNotAvailableException
import com.acemirr.cleanarchitecture.data.utils.ResState
import com.acemirr.cleanarchitecture.external.logError
import com.acemirr.cleanarchitecture.external.showToast
import com.github.kittinunf.fuel.core.Request
import com.github.kittinunf.fuel.core.Response
import com.github.kittinunf.fuel.core.ResponseDeserializable
import com.github.kittinunf.fuel.coroutines.awaitObjectResult
import com.github.kittinunf.fuel.gson.gsonDeserializer
import kotlinx.coroutines.*

class Network {

    @Suppress("UNCHECKED_CAST", "IMPLICIT_NOTHING_AS_TYPE_PARAMETER")
    inline fun <reified T : Any> api(context: Context, noinline request: suspend () -> Request):ResState<T> {
        val res = runBlocking res@{
            try {
                request.invoke().awaitObjectResult(object : ResponseDeserializable<T> {
                    override fun deserialize(response: Response): T {
                        return gsonDeserializer<T>().deserialize(response)
                    }
                }).fold({
                    return@res ResState.Success(it)
                }, {
                    showToast(context,"UnSuccessFul # code : ${it.message}")
                    return@res ResState.Error<T>(DataNotAvailableException())
                })
            } catch (throwable: Throwable) {
                if (throwable is CancellationException) {
                    logError("Network # Throwable ==> job canceled")
                } else {
                    showToast(context,"Throwable ${throwable.message})}")
                    throwable.printStackTrace()
                }
            }
        }
        logError(context.packageName)
        return res as ResState<T>
    }

    fun showToast(context: Context, msg:String?) = GlobalScope.launch(Dispatchers.Main) {
        if (msg != null)
            context.showToast(msg)
    }
}