package com.acemirr.training_task_1.utils

import androidx.recyclerview.widget.DiffUtil
import com.acemirr.training_task_1.data.model.PagingModel

class AdapterCallback {

    companion object {

        /**
         * diff callback adapter notification
         */
        val DiffPagingCallback = object : DiffUtil.ItemCallback<PagingModel>() {
            override fun areItemsTheSame(oldItem: PagingModel, newItem: PagingModel): Boolean {
                return oldItem.message == newItem.message
            }

            override fun areContentsTheSame(oldItem: PagingModel, newItem: PagingModel): Boolean {
                return oldItem == newItem
            }

        }

    }



}