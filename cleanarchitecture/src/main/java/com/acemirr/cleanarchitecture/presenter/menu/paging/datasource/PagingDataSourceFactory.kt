package com.acemirr.cleanarchitecture.presenter.menu.paging.datasource

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.acemirr.cleanarchitecture.data.model.PagingNewsModel
import com.acemirr.cleanarchitecture.domain.usecase.PagingUseCase


class PagingDataSourceFactory(private val ctx:Context, private val useCase: PagingUseCase): DataSource.Factory<Int, PagingNewsModel> () {

    val pagingDataSourceLiveData: MutableLiveData<PagingDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, PagingNewsModel> {
        val pagingDataSource = PagingDataSource(ctx, useCase)
        pagingDataSourceLiveData.postValue(pagingDataSource)
        return pagingDataSource
    }
}