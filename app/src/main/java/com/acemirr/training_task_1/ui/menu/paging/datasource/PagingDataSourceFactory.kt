package com.acemirr.training_task_1.ui.menu.paging.datasource

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.acemirr.training_task_1.data.model.PagingModel

class PagingDataSourceFactory: DataSource.Factory<Int, PagingModel> () {

    val pagingDataSourceLiveData: MutableLiveData<PagingDataSource> = MutableLiveData()

    override fun create(): DataSource<Int, PagingModel> {
        val pagingDataSource = PagingDataSource()
        pagingDataSourceLiveData.postValue(pagingDataSource)
        return pagingDataSource
    }
}