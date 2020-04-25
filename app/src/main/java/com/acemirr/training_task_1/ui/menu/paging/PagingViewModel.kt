package com.acemirr.training_task_1.ui.menu.paging

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.acemirr.training_task_1.data.model.PagingModel
import com.acemirr.training_task_1.ui.menu.paging.datasource.PagingDataSource
import com.acemirr.training_task_1.ui.menu.paging.datasource.PagingDataSourceFactory
import com.acemirr.training_task_1.utils.LoadingState

class PagingViewModel(application: Application) : AndroidViewModel(application) {

    private var pagingDataSourceFactory: PagingDataSourceFactory = PagingDataSourceFactory()
    var pagingList: LiveData<PagedList<PagingModel>>

    init {
        val config = PagedList.Config.Builder()
            .setPageSize(20)
            .setPrefetchDistance(5)
            .setInitialLoadSizeHint(20)
            .setEnablePlaceholders(false)
            .build()
        pagingList = LivePagedListBuilder(pagingDataSourceFactory, config).build()
    }

    fun getLoadingState(): LiveData<LoadingState> {
        return Transformations.switchMap(pagingDataSourceFactory.pagingDataSourceLiveData, PagingDataSource::state)
    }

    fun refreshListNotification() {
        pagingDataSourceFactory.pagingDataSourceLiveData.value?.invalidate()
    }

}
