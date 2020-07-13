package com.acemirr.cleanarchitecture.di.provider

import com.acemirr.cleanarchitecture.di.menu.list.PagingModule
import com.acemirr.cleanarchitecture.presenter.menu.paging.view.PagingFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class PagingFragmentProvider {

    @ContributesAndroidInjector(modules = [PagingModule::class])
    abstract fun providePagingFragment(): PagingFragment

}