package com.acemirr.training_task_1.ui.menu.paging.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.viewModelScope
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.acemirr.training_task_1.ui.menu.paging.model.News
import com.acemirr.training_task_1.data.repository.PagingRepo
//import com.acemirr.training_task_1.data.model.PagingModel
import com.acemirr.training_task_1.ui.menu.paging.datasource.PagingDataSource
import com.acemirr.training_task_1.ui.menu.paging.datasource.PagingDataSourceFactory
import com.acemirr.training_task_1.utils.LoadingState

class PagingViewModel(application: Application) : AndroidViewModel(application) {
    private val pagingRepo = PagingRepo(viewModelScope)
    private var pagingDataSourceFactory: PagingDataSourceFactory = PagingDataSourceFactory(application.applicationContext,pagingRepo)
    var pagingList: LiveData<PagedList<News>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(5)
            .setInitialLoadSizeHint(10)
            .setEnablePlaceholders(false)
            .build()
        pagingList = LivePagedListBuilder<Int, News>(pagingDataSourceFactory, config).build()
    }

    fun getLoadingState(): LiveData<LoadingState> {
        return Transformations.switchMap(pagingDataSourceFactory.pagingDataSourceLiveData, PagingDataSource::state)
    }

}
