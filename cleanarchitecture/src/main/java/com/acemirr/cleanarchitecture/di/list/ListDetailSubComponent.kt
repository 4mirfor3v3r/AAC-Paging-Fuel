package com.acemirr.cleanarchitecture.di.list

import androidx.fragment.app.ListFragment
import dagger.Subcomponent

@Subcomponent(modules = [ListModule::class])
interface ListDetailSubComponent {
    fun inject(listFragment: ListFragment)
}