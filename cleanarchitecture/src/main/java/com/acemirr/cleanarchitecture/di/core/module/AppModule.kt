package com.acemirr.cleanarchitecture.di.core.module

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.preference.PreferenceManager
import com.acemirr.cleanarchitecture.data.utils.DiskExecutor
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module(includes = [DaggerViewModelFactoryModule::class])
class AppModule  {

    @Singleton
    @Provides
    fun provideContext(application: Application): Context = application.applicationContext

    @Singleton
    @Provides
    fun provideSharedPreferences(context: Context): SharedPreferences =
        PreferenceManager.getDefaultSharedPreferences(context)

    @Singleton
    @Provides
    fun provideDiskExecutor(): DiskExecutor {
        return DiskExecutor()
    }

}