package com.acemirr.cleanarchitecture.di.core.module

import android.content.Context
import androidx.room.Room
import com.acemirr.cleanarchitecture.data.source.local.AppDatabase
import com.acemirr.cleanarchitecture.data.source.local.dao.ListDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Provides
    @Singleton
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, AppDatabase.DB_NAME)
            .fallbackToDestructiveMigrationFrom()
            .allowMainThreadQueries().build()
    }

    @Provides
    fun provideListDao(database: AppDatabase): ListDao {
        return database.dao
    }
}