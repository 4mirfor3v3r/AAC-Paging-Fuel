package com.acemirr.cleanarchitecture.di.provider

import com.acemirr.cleanarchitecture.di.menu.list.ListModule
import com.acemirr.cleanarchitecture.presenter.menu.list.view.ListFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ListFragmentProvider {

    @ContributesAndroidInjector(modules = [ListModule::class])
    abstract fun provideAlbumsFragment(): ListFragment

}