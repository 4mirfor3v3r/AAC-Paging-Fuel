package com.acemirr.cleanarchitecture.external

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.DiffUtil
import com.acemirr.cleanarchitecture.BuildConfig

fun logDebug(message: String) {
    if (BuildConfig.DEBUG) Log.d(Constant.TAG_DEBUG, message)
}

fun logError(message: String) {
    if (BuildConfig.DEBUG) Log.e(Constant.TAG_ERROR, message)
}

fun Context.showToast(message: String){
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
}

//    STILL IN DEVELOPMENT
fun <T> Class<T>.genericRvDiffUtil(paramKey: Int) = object : DiffUtil.ItemCallback<T>() {
    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return try {
            val old = oldItem as Class<*>
            val new = newItem as Class<*>

            old.fields[paramKey] == new.fields[paramKey]
        }catch (e:ClassCastException){
            e.printStackTrace()
            false
        }
    }
}

fun < T:Class<*>> genericDiffUtil(paramKey: Int) = object :DiffUtil.ItemCallback<T>(){
//    override fun areContentsTheSame(oldItem: Class<Any>, newItem: Class<Any>): Boolean {
//        return this@genericDiffUtil.fields[paramKey] == newItem.fields[paramKey]
//    }

//    override fun areItemsTheSame(oldItem: Class<T>, newItem: Class<T>): Boolean {
//        return oldItem == newItem
//    }
//
//    override fun areContentsTheSame(oldItem: Class<T>, newItem: Class<T>): Boolean {
//        return oldItem.fields[paramKey] == newItem.fields[paramKey]
//    }

    override fun areItemsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: T, newItem: T): Boolean {
        return oldItem.fields[paramKey] == newItem.fields[paramKey]
    }
}