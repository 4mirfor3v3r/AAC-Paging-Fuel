package com.acemirr.cleanarchitecture.di.menu.list

import com.acemirr.cleanarchitecture.presenter.menu.paging.view.PagingFragment
import dagger.Subcomponent

@Subcomponent(modules = [PagingModule::class])
interface PagingSubComponent {
    fun inject(fragment: PagingFragment)
}