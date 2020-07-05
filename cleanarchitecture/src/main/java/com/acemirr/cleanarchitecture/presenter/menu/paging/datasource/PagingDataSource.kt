package com.acemirr.cleanarchitecture.presenter.menu.paging.datasource

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.acemirr.cleanarchitecture.data.model.News
import com.acemirr.cleanarchitecture.domain.usecase.PagingUseCase
import com.acemirr.cleanarchitecture.external.LoadingState

class PagingDataSource(private val ctx: Context, private val usecase: PagingUseCase) :PageKeyedDataSource<Int, News>() {

    var state: MutableLiveData<LoadingState> = MutableLiveData()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, News>) {
        updateState(LoadingState.LOADING)
        usecase.getPaging(ctx, listOf("page" to 1,"pageSize" to params.requestedLoadSize),{
            callback.onResult(it,null,2)
        },{
            updateState(LoadingState.DONE)
        })
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
        updateState(LoadingState.LOADING)
        usecase.getPaging(ctx,listOf("page" to params.key,"pageSize" to params.requestedLoadSize),{
            callback.onResult(it,params.key + 1)
        },{
            updateState(LoadingState.DONE)
        })
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, News>) {
    }

    private fun updateState(state: LoadingState) {
        this.state.postValue(state)
    }
}