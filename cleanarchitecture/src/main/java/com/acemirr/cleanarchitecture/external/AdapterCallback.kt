package com.acemirr.cleanarchitecture.external

import androidx.recyclerview.widget.DiffUtil
import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel
import com.acemirr.cleanarchitecture.data.model.PagingNewsModel

class AdapterCallback {

    companion object {

        val DiffDetailGridCallback = object : DiffUtil.ItemCallback<GridGalleryModel>() {
            override fun areItemsTheSame(oldItem: GridGalleryModel, newItem: GridGalleryModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GridGalleryModel, newItem: GridGalleryModel): Boolean {
                return oldItem.caption == newItem.caption
            }
        }
        val DiffListCallback = object : DiffUtil.ItemCallback<ListPlaceModel>() {
            override fun areItemsTheSame(
                oldItem: ListPlaceModel,
                newItem: ListPlaceModel
            ): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: ListPlaceModel,
                newItem: ListPlaceModel
            ): Boolean {
                return oldItem.name == newItem.name
            }

        }
        val DiffGridCallback = object : DiffUtil.ItemCallback<GridGalleryModel>() {
            override fun areItemsTheSame(oldItem: GridGalleryModel, newItem: GridGalleryModel): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: GridGalleryModel, newItem: GridGalleryModel): Boolean {
                return oldItem.caption == newItem.caption
            }

        }

        /**
         * diff callback adapter notification
         */
        val DiffPagingCallback = object : DiffUtil.ItemCallback<PagingNewsModel>() {
            override fun areItemsTheSame(oldItem: PagingNewsModel, newItem: PagingNewsModel): Boolean {
                return oldItem.title == newItem.title
            }

            override fun areContentsTheSame(oldItem: PagingNewsModel, newItem: PagingNewsModel): Boolean {
                return oldItem == newItem
            }

        }
    }
}