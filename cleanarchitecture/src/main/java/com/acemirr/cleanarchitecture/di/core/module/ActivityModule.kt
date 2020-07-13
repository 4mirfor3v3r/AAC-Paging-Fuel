package com.acemirr.cleanarchitecture.di.core.module

import android.app.Application
import com.acemirr.cleanarchitecture.CleanApplication
import com.acemirr.cleanarchitecture.di.provider.*
import com.acemirr.cleanarchitecture.presenter.activities.MainActivity
import dagger.Binds
import dagger.Module
import dagger.android.ContributesAndroidInjector
import dagger.android.support.AndroidSupportInjectionModule


@Module(includes = [AndroidSupportInjectionModule::class])
interface ActivityModule {

    @ContributesAndroidInjector(modules = [ListFragmentProvider::class, ListDetailFragmentProvider::class, GridFragmentProvider::class, GridDetailFragmentProvider::class, PagingFragmentProvider::class])
    fun mainActivityInjector(): MainActivity

    @Binds
    fun bindApplication(app: CleanApplication): Application

}