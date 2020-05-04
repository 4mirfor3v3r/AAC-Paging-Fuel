package com.acemirr.training_task_1.utils

import androidx.recyclerview.widget.DiffUtil
import com.acemirr.training_task_1.ui.menu.grid.model.GridModel
import com.acemirr.training_task_1.ui.menu.list.model.ListModel
import com.acemirr.training_task_1.ui.menu.paging.model.News
//import com.acemirr.training_task_1.data.model.PagingModel

class AdapterCallback {

    companion object {

        val DiffDetailGridCallback = object : DiffUtil.ItemCallback<GridModel>(){
            override fun areItemsTheSame(oldItem: GridModel, newItem: GridModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GridModel, newItem: GridModel): Boolean {
                return oldItem.caption == newItem.caption
            }
        }
        val DiffListCallback = object :DiffUtil.ItemCallback<ListModel>(){
            override fun areItemsTheSame(oldItem: ListModel, newItem: ListModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ListModel, newItem: ListModel): Boolean {
                return oldItem.name == newItem.name
            }

        }
        val DiffGridCallback = object : DiffUtil.ItemCallback<GridModel>() {
            override fun areItemsTheSame(oldItem: GridModel, newItem: GridModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GridModel, newItem: GridModel): Boolean {
                return oldItem.caption == newItem.caption
            }

        }

        /**
         * diff callback adapter notification
         */
        val DiffPagingCallback = object : DiffUtil.ItemCallback<News>() {
            override fun areItemsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: News, newItem: News): Boolean {
                return oldItem == newItem
            }

        }
//        val DiffPagingCallback = object : DiffUtil.ItemCallback<PagingModel>() {
//            override fun areItemsTheSame(oldItem: PagingModel, newItem: PagingModel): Boolean {
//                return oldItem.message == newItem.message
//            }
//
//            override fun areContentsTheSame(oldItem: PagingModel, newItem: PagingModel): Boolean {
//                return oldItem == newItem
//            }
//
//        }

    }



}