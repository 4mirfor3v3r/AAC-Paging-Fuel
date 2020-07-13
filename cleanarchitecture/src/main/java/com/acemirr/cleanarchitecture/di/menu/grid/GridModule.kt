package com.acemirr.cleanarchitecture.di.menu.grid

import androidx.lifecycle.ViewModel
import com.acemirr.cleanarchitecture.di.key.ViewModelKey
import com.acemirr.cleanarchitecture.presenter.menu.grid.viewmodel.GridViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class GridModule {

    @Binds
    @IntoMap
    @ViewModelKey(GridViewModel::class)
    abstract fun bindListViewModel(viewModel: GridViewModel) : ViewModel
}