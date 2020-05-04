package com.acemirr.training_task_1.ui.menu.paging.datasource

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.acemirr.training_task_1.data.repository.PagingRepo
import com.acemirr.training_task_1.ui.menu.paging.model.News


class PagingDataSourceFactory(private val ctx:Context, private val pagingRepo: PagingRepo): DataSource.Factory<Int, News> () {

    val pagingDataSourceLiveData: MutableLiveData<PagingDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, News> {
        val pagingDataSource = PagingDataSource(ctx, pagingRepo)
        pagingDataSourceLiveData.postValue(pagingDataSource)
        return pagingDataSource
    }
}