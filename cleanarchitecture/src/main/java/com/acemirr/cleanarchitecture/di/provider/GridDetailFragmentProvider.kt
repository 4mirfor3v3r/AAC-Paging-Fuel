package com.acemirr.cleanarchitecture.di.provider

import com.acemirr.cleanarchitecture.di.grid.GridDetailModule
import com.acemirr.cleanarchitecture.presenter.menu.grid.view.DetailGridFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector


@Module
abstract class GridDetailFragmentProvider{


    @ContributesAndroidInjector(modules = [GridDetailModule::class])
    abstract fun providePhotosFragment(): DetailGridFragment
}