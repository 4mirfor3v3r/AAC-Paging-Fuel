package com.acemirr.cleanarchitecture.di.grid

import androidx.lifecycle.ViewModel
import com.acemirr.cleanarchitecture.di.key.ViewModelKey
import com.acemirr.cleanarchitecture.presenter.menu.grid.viewmodel.DetailGridViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GridDetailModule {

    @Binds
    @IntoMap
    @ViewModelKey(DetailGridViewModel::class)
    abstract fun bindListDetailViewModel(viewModel: DetailGridViewModel) : ViewModel
}