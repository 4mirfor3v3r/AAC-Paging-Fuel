package com.acemirr.cleanarchitecture.di.core.module

import android.content.Context
import androidx.room.Room
import com.acemirr.cleanarchitecture.data.source.local.AppDatabase
import com.acemirr.cleanarchitecture.data.source.local.dao.GridDao
import com.acemirr.cleanarchitecture.data.source.local.dao.ListDao
import com.acemirr.cleanarchitecture.data.source.local.dao.PagingDao
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
        return database.listDao
    }

    @Provides
    fun provideGridDao(database: AppDatabase): GridDao {
        return database.gridDao
    }

    @Provides
    fun providePagingDao(database: AppDatabase): PagingDao {
        return database.pagingDao
    }
}