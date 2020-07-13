package com.acemirr.cleanarchitecture.di.core.module

import dagger.Module
import dagger.Provides
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers

@Module
class CoroutineModule {

    @Provides
    fun coroutineIODispatcher() = CoroutineScope(Dispatchers.IO)

}