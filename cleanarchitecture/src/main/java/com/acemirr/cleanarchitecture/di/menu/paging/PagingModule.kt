package com.acemirr.cleanarchitecture.di.menu.list

import androidx.lifecycle.ViewModel
import com.acemirr.cleanarchitecture.di.key.ViewModelKey
import com.acemirr.cleanarchitecture.presenter.menu.paging.viewmodel.PagingViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class PagingModule {

    @Binds
    @IntoMap
    @ViewModelKey(PagingViewModel::class)
    abstract fun bindPagingViewModel(list: PagingViewModel) : ViewModel
}