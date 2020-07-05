package com.acemirr.cleanarchitecture.presenter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acemirr.cleanarchitecture.data.api.ApiServiceImpl
import com.acemirr.cleanarchitecture.data.repository.GridRepositoryImpl
import com.acemirr.cleanarchitecture.data.repository.ListRepositoryImpl
import com.acemirr.cleanarchitecture.data.repository.PagingRepositoryImpl
import com.acemirr.cleanarchitecture.domain.usecase.GridUseCase
import com.acemirr.cleanarchitecture.domain.usecase.ListUseCase
import com.acemirr.cleanarchitecture.domain.usecase.PagingUseCase
import com.acemirr.cleanarchitecture.presenter.menu.grid.viewmodel.DetailGridViewModel
import com.acemirr.cleanarchitecture.presenter.menu.grid.viewmodel.GridViewModel
import com.acemirr.cleanarchitecture.presenter.menu.list.viewmodel.ListViewModel
import com.acemirr.cleanarchitecture.presenter.menu.paging.viewmodel.PagingViewModel
import kotlinx.coroutines.CoroutineScope

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val scope: CoroutineScope) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
            modelClass.isAssignableFrom(ListViewModel::class.java) ->
                ListViewModel(ListUseCase(ListRepositoryImpl(ApiServiceImpl(scope)))) as T

            modelClass.isAssignableFrom(GridViewModel::class.java) ->
                GridViewModel(GridUseCase(GridRepositoryImpl(ApiServiceImpl(scope)))) as T

            modelClass.isAssignableFrom(DetailGridViewModel::class.java) ->
                DetailGridViewModel(GridUseCase(GridRepositoryImpl(ApiServiceImpl(scope)))) as T

            modelClass.isAssignableFrom(PagingViewModel::class.java) ->
                PagingViewModel(PagingUseCase(PagingRepositoryImpl(ApiServiceImpl(scope)))) as T

//            modelClass.isAssignableFrom(Detail::class.java) ->
//                FavoriteActivityViewModel(FavoriteRepo(context, scope)) as T

            else -> throw IllegalArgumentException("Unknown Class Name")
        }
    }
}