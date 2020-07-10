package com.acemirr.cleanarchitecture.data.utils

import com.acemirr.cleanarchitecture.data.model.GridModel
import com.acemirr.cleanarchitecture.data.model.ListPlaceRemote
import com.acemirr.cleanarchitecture.domain.model.LocalGridModel
import com.acemirr.cleanarchitecture.domain.model.LocalListModel

object Mapper {
    fun toRemote(local: LocalListModel): ListPlaceRemote {
        return ListPlaceRemote(
            local.name,
            local.location,
            local.description,
            local.thumbnail,
            local.image
        )
    }
    fun toLocal(local: ListPlaceRemote): LocalListModel {
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

    fun toLocalList(remote: MutableList<ListPlaceRemote>): List<LocalListModel> {
        return remote.map {
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
    fun toRemoteList(local: MutableList<LocalListModel>): List<ListPlaceRemote> {
        return local.map {
            return@map ListPlaceRemote(
                it.name,
                it.location,
                it.description,
                it.thumbnail,
                it.image
            )
        }
    }

    fun toLocalGrid(remote: MutableList<GridModel>): List<LocalGridModel> {
        return remote.map {
            return@map LocalGridModel(
                null,
                it.caption,
                it.image,
                it.thumbnail
            )
        }
    }
    fun toRemoteGrid(local: MutableList<LocalGridModel>): List<GridModel> {
        return local.map {
            return@map GridModel(
                it.caption,
                it.image,
                it.thumbnail
            )
        }
    }
}