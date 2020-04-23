package com.acemirr.training_task_1.ui.paging.datasource

import android.os.SystemClock
import androidx.lifecycle.MutableLiveData
import androidx.paging.PageKeyedDataSource
import com.acemirr.training_task_1.data.model.PagingModel
import com.acemirr.training_task_1.utils.Constants
import com.acemirr.training_task_1.utils.LoadingState
import com.acemirr.training_task_1.utils.logDebug

class PagingDataSource: PageKeyedDataSource<Int, PagingModel>() {

    var state: MutableLiveData<LoadingState> = MutableLiveData()
    var listNotification: MutableList<PagingModel> = mutableListOf()

    override fun loadInitial(params: LoadInitialParams<Int>, callback: LoadInitialCallback<Int, PagingModel>) {
        logDebug("NotificationDataSource # loadInitial ${params.requestedLoadSize}")
        updateState(LoadingState.LOADING)
        listNotification.clear()
        for (i:Int in 1..20) {
            listNotification.add(PagingModel(i, "Paging # $i"))
        }
        updateState(LoadingState.DONE)
        callback.onResult(listNotification, 0, 20)
    }

    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, PagingModel>) {
        logDebug("NotificationDataSource # loadAfter ${params.key}/${params.requestedLoadSize}")
        updateState(LoadingState.LOADING)
        listNotification.clear()
        for (i:Int in 1..20) {
            listNotification.add(PagingModel(i, "Loadmore # $i"))
        }
        SystemClock.sleep(Constants.DUMMY_LOAD_MORE_TIME)
        updateState(LoadingState.DONE)
        callback.onResult(listNotification, params.key+20)
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, PagingModel>) {

    }

    private fun updateState(state: LoadingState){
        this.state.postValue(state)
    }
}