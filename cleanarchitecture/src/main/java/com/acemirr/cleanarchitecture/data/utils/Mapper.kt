package com.acemirr.cleanarchitecture.data.utils

import com.acemirr.cleanarchitecture.data.model.GridGalleryModel
import com.acemirr.cleanarchitecture.data.model.ListPlaceModel
import com.acemirr.cleanarchitecture.data.model.PagingNewsModel
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalGridModel
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalListModel
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalNewsModel

object Mapper {
    fun toRemote(local: LocalListModel): ListPlaceModel {
        return ListPlaceModel(
            local.name,
            local.location,
            local.description,
            local.thumbnail,
            local.image
        )
    }

    fun toLocal(local: ListPlaceModel): LocalListModel {
        return local.let {
            return@let LocalListModel(
                null,
                it.name,
                it.location,
                it.description,
                it.thumbnail,
                it.image
            )
        }
    }

    fun toLocalList(model: MutableList<ListPlaceModel>): List<LocalListModel> {
        return model.map {
            return@map LocalListModel(
                null,
                it.name,
                it.location,
                it.description,
                it.thumbnail,
                it.image
            )
        }
    }

    fun toRemoteList(local: MutableList<LocalListModel>): List<ListPlaceModel> {
        return local.map {
            return@map ListPlaceModel(
                it.name,
                it.location,
                it.description,
                it.thumbnail,
                it.image
            )
        }
    }

    fun toLocalGrid(remote: MutableList<GridGalleryModel>): List<LocalGridModel> {
        return remote.map {
            return@map LocalGridModel(
                null,
                it.caption,
                it.image,
                it.thumbnail
            )
        }
    }

    fun toRemoteGrid(local: MutableList<LocalGridModel>): List<GridGalleryModel> {
        return local.map {
            return@map GridGalleryModel(
                it.caption,
                it.image,
                it.thumbnail
            )
        }
    }

    fun toPagingNewsRemote(local: List<LocalNewsModel>): List<PagingNewsModel> {
        return local.map { return@map PagingNewsModel(it.title, it.image) }
    }

    fun toPagingNewsLocal(local: List<PagingNewsModel>): List<LocalNewsModel> {
        return local.map { return@map LocalNewsModel(
            null,
            it.title,
            it.image
        )
        }
    }
}