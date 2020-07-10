package com.acemirr.cleanarchitecture.di.core.module

import androidx.lifecycle.ViewModelProvider
import com.acemirr.cleanarchitecture.presenter.base.DaggersViewModelFactory
import dagger.Binds
import dagger.Module


@Module
abstract class DaggerViewModelFactoryModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: DaggersViewModelFactory):ViewModelProvider.Factory
}