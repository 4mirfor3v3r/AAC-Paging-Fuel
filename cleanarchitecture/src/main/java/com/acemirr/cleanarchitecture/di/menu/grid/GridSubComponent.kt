package com.acemirr.cleanarchitecture.di.menu.grid

import com.acemirr.cleanarchitecture.di.menu.grid.GridModule
import com.acemirr.cleanarchitecture.presenter.menu.grid.view.GridFragment
import dagger.Subcomponent

@Subcomponent(modules = [GridModule::class])
interface GridSubComponent {
    fun inject(fragment: GridFragment)
}