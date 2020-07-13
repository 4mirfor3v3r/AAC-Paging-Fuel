package com.acemirr.cleanarchitecture.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acemirr.cleanarchitecture.data.source.local.dao.GridDao
import com.acemirr.cleanarchitecture.data.source.local.dao.ListDao
import com.acemirr.cleanarchitecture.data.source.local.dao.PagingDao
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalGridModel
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalListModel
import com.acemirr.cleanarchitecture.data.source.local.entity.LocalNewsModel

@Database(
    entities = [LocalListModel::class, LocalGridModel::class, LocalNewsModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val listDao: ListDao
    abstract val gridDao: GridDao
    abstract val pagingDao: PagingDao

    companion object {
        const val DB_NAME = "com.acemirr.cleanarchitecture.DATABASE.db"
    }
}