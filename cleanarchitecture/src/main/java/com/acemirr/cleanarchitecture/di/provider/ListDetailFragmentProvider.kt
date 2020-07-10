package com.acemirr.cleanarchitecture.di.provider

import com.acemirr.cleanarchitecture.di.list.ListDetailModule
import com.acemirr.cleanarchitecture.presenter.menu.list.view.DetailListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class ListDetailFragmentProvider{


    @ContributesAndroidInjector(modules = [ListDetailModule::class])
    abstract fun providePhotosFragment(): DetailListFragment
}