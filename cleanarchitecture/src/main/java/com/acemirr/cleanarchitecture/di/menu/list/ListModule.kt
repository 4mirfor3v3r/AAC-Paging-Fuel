package com.acemirr.cleanarchitecture.di.menu.list

import androidx.lifecycle.ViewModel
import com.acemirr.cleanarchitecture.di.key.ViewModelKey
import com.acemirr.cleanarchitecture.presenter.menu.list.viewmodel.ListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ListModule {

    @Binds
    @IntoMap
    @ViewModelKey(ListViewModel::class)
    abstract fun bindListViewModel(list: ListViewModel) : ViewModel
}