package com.acemirr.cleanarchitecture.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acemirr.cleanarchitecture.data.source.local.dao.ListDao
import com.acemirr.cleanarchitecture.domain.model.LocalGridModel
import com.acemirr.cleanarchitecture.domain.model.LocalListModel

@Database(entities = [LocalListModel::class,LocalGridModel::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
        abstract val dao : ListDao

        companion object {
                const val DB_NAME = "com.acemirr.cleanarchitecture.DATABASE.db"
        }
}