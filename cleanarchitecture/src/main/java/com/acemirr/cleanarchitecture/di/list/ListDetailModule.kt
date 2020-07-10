package com.acemirr.cleanarchitecture.di.list

import androidx.lifecycle.ViewModel
import com.acemirr.cleanarchitecture.di.key.ViewModelKey
import com.acemirr.cleanarchitecture.presenter.menu.list.viewmodel.DetailListViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ListDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailListViewModel::class)
    abstract fun bindListDetailViewModel(viewModel: DetailListViewModel) : ViewModel
}