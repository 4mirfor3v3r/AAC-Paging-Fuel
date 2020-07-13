package com.acemirr.cleanarchitecture.di.provider

import com.acemirr.cleanarchitecture.di.menu.grid.GridModule
import com.acemirr.cleanarchitecture.presenter.menu.grid.view.GridFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class GridFragmentProvider {

    @ContributesAndroidInjector(modules = [GridModule::class])
    abstract fun provideAlbumsFragment(): GridFragment

}