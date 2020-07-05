package com.acemirr.cleanarchitecture.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.acemirr.cleanarchitecture.data.db.entity.ListLocal

@Database(entities = [ListLocal::class], version = 1, exportSchema = false)
abstract class PlaceDatabase : RoomDatabase() {
        abstract fun dao(): PlaceDao
}