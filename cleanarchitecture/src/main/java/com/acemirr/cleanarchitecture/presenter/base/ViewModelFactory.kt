package com.acemirr.cleanarchitecture.presenter.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.acemirr.cleanarchitecture.data.repository.PagingRepositoryImpl
import com.acemirr.cleanarchitecture.data.source.remote.ApiServiceImpl
import com.acemirr.cleanarchitecture.domain.usecase.PagingUseCase
import com.acemirr.cleanarchitecture.presenter.menu.paging.viewmodel.PagingViewModel
import kotlinx.coroutines.CoroutineScope

@Suppress("UNCHECKED_CAST")
class ViewModelFactory(private val scope: CoroutineScope) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return when {
//            modelClass.isAssignableFrom(ListViewModel::class.java) ->
//                ListViewModel(
//                    ListUseCase(
//                        ListRepositoryImpl(
//                            ListLocalDataSource(DiskExecutor(),ListDao_Impl(AppDatabase_Impl())),
//                            ListRemoteDataSource(ApiServiceImpl(scope))
//                )
//                    )
//                ) as T

//            modelClass.isAssignableFrom(GridViewModel::class.java) ->
//                GridViewModel(GridUseCase(GridRepositoryImpl(
//                    ApiServiceImpl(
//                        scope
//                    )
//                ))) as T

//            modelClass.isAssignableFrom(DetailGridViewModel::class.java) ->
//                DetailGridViewModel(GridUseCase(GridRepositoryImpl(
//                    ApiServiceImpl(
//                        scope
//                    )
//                ))) as T

            modelClass.isAssignableFrom(PagingViewModel::class.java) ->
                PagingViewModel(PagingUseCase(PagingRepositoryImpl(
                    ApiServiceImpl(
                        scope
                    )
                ))) as T

//            modelClass.isAssignableFrom(DetailListViewModel::class.java) ->
//                DetailListViewModel(ListUseCase(ListRepositoryImpl(
//                    ApiServiceImpl(
//                        scope
//                    )
//                ))) as T

            else -> throw IllegalArgumentException("Unknown Class Name")
        }
    }
}