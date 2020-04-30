package com.acemirr.training_task_1.ui.menu.paging.datasource

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.acemirr.training_task_1.data.model.News
import com.acemirr.training_task_1.data.repository.PagingRepo
import com.acemirr.training_task_1.utils.LoadingState
import kotlinx.coroutines.cancel

class PagingDataSource(private val ctx: Context, private val pagingRepo: PagingRepo) :PageKeyedDataSource<Int,News>() {

    var state: MutableLiveData<LoadingState> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, News>) {
        updateState(LoadingState.LOADING)
        pagingRepo.getNewsPaging(ctx, listOf("page" to 1,"pageSize" to params.requestedLoadSize),{
            if (it != null) {
                callback.onResult(it.news,null,2)
            }
        },{
            updateState(LoadingState.DONE)
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        updateState(LoadingState.LOADING)
        pagingRepo.getNewsPaging(ctx,listOf("page" to params.key,"pageSize" to params.requestedLoadSize),{
            if (it != null){
                callback.onResult(it.news,params.key + 1)
            }
        },{
            updateState(LoadingState.DONE)
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
    }

    private fun updateState(state: LoadingState) {
        this.state.postValue(state)
    }

    override fun invalidate() {
        pagingRepo.coroutineScope.cancel()
        super.invalidate()
    }
}