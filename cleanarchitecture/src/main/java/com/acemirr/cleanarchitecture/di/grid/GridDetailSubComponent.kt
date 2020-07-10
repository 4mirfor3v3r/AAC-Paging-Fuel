package com.acemirr.cleanarchitecture.di.grid

import com.acemirr.cleanarchitecture.presenter.menu.grid.view.DetailGridFragment
import dagger.Subcomponent

@Subcomponent(modules = [GridDetailModule::class])
interface GridDetailSubComponent {
    fun inject(fragment: DetailGridFragment)
}