package com.acemirr.cleanarchitecture.presenter.menu.paging.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.acemirr.cleanarchitecture.data.model.PagingNewsModel
import com.acemirr.cleanarchitecture.domain.usecase.PagingUseCase
import com.acemirr.cleanarchitecture.external.LoadingState
import com.acemirr.cleanarchitecture.presenter.menu.paging.datasource.PagingDataSource
import com.acemirr.cleanarchitecture.presenter.menu.paging.datasource.PagingDataSourceFactory
import javax.inject.Inject

class PagingViewModel @Inject constructor(val useCase: PagingUseCase) : ViewModel() {

    var pagingDataSourceFactory :PagingDataSourceFactory ?=null
    var pagingList: LiveData<PagedList<PagingNewsModel>>? =null

    fun setPaging(ctx:Context){
        val config = PagedList.Config.Builder()
            .setPageSize(5)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()
        pagingDataSourceFactory = PagingDataSourceFactory(ctx,useCase)

        if (pagingDataSourceFactory != null)
            pagingList = LivePagedListBuilder(pagingDataSourceFactory!!, config).build()
    }

    fun getLoadingState(): LiveData<LoadingState>? {
        if (pagingDataSourceFactory != null)
            return Transformations.switchMap(pagingDataSourceFactory!!.pagingDataSourceLiveData, PagingDataSource::state)
        else
            return null
    }

}
