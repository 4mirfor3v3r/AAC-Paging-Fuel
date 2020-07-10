package com.acemirr.cleanarchitecture.data.utils

sealed class ResState<T> {
    data class Success<T>(val data: T) : ResState<T>()
    data class Error<T>(val error: Throwable) : ResState<T>()
}