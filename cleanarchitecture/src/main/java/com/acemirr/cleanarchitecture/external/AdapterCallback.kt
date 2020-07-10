package com.acemirr.cleanarchitecture.external

import androidx.recyclerview.widget.DiffUtil
import com.acemirr.cleanarchitecture.data.model.GridModel
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote
import com.acemirr.cleanarchitecture.data.model.News

class AdapterCallback {

    companion object {

        val DiffDetailGridCallback = object : DiffUtil.ItemCallback<GridModel>() {
            override fun areItemsTheSame(oldItem: GridModel, newItem: GridModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GridModel, newItem: GridModel): Boolean {
                return oldItem.caption == newItem.caption
            }
        }
        val DiffListCallback = object : DiffUtil.ItemCallback<ListPlaceRemote>() {
            override fun areItemsTheSame(
                oldItem: ListPlaceRemote,
                newItem: ListPlaceRemote
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ListPlaceRemote,
                newItem: ListPlaceRemote
            ): Boolean {
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
    }
}