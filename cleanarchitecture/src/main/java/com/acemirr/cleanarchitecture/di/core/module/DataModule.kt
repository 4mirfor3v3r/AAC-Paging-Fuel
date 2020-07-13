package com.acemirr.cleanarchitecture.di.core.module

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import com.acemirr.cleanarchitecture.data.datasource.grid.GridDataSource
import com.acemirr.cleanarchitecture.data.datasource.grid.GridLocalDataSource
import com.acemirr.cleanarchitecture.data.datasource.grid.GridRemoteDataSource
import com.acemirr.cleanarchitecture.data.datasource.list.ListDataSource
import com.acemirr.cleanarchitecture.data.datasource.list.ListLocalDataSource
import com.acemirr.cleanarchitecture.data.datasource.list.ListRemoteDataSource
import com.acemirr.cleanarchitecture.data.datasource.paging.PagingDataSource
import com.acemirr.cleanarchitecture.data.datasource.paging.PagingLocalDataSource
import com.acemirr.cleanarchitecture.data.datasource.paging.PagingRemoteDataSource
import com.acemirr.cleanarchitecture.data.repository.GridRepositoryImpl
import com.acemirr.cleanarchitecture.data.repository.ListRepositoryImpl
import com.acemirr.cleanarchitecture.data.repository.PagingRepositoryImpl
import com.acemirr.cleanarchitecture.data.source.local.dao.GridDao
import com.acemirr.cleanarchitecture.data.source.local.dao.ListDao
import com.acemirr.cleanarchitecture.data.source.local.dao.PagingDao
import com.acemirr.cleanarchitecture.data.source.remote.ApiServiceImpl
import com.acemirr.cleanarchitecture.data.utils.DiskExecutor
import com.acemirr.cleanarchitecture.domain.repository.GridRepository
import com.acemirr.cleanarchitecture.domain.repository.ListRepository
import com.acemirr.cleanarchitecture.domain.repository.PagingRepository
import com.acemirr.cleanarchitecture.domain.usecase.GridUseCase
import com.acemirr.cleanarchitecture.domain.usecase.ListUseCase
import com.acemirr.cleanarchitecture.domain.usecase.PagingUseCase
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DataModule {

    @Provides
    @Singleton
    fun provideApiServiceImpl(): ApiServiceImpl {
        return ApiServiceImpl()
    }

    @Provides
    @Singleton
    fun provideCoroutineModule():CoroutineModule{
        return CoroutineModule()
    }

    @Provides
    @Singleton
    fun provideListRepository(movieRemote: ListDataSource.Remote, movieLocal: ListDataSource.Local): ListRepository {
        return ListRepositoryImpl(movieLocal, movieRemote)
    }

    @Provides
    @Singleton
    fun provideListLocalDataSource(executor: DiskExecutor, coroutineModule: CoroutineModule, movieDao: ListDao): ListDataSource.Local {
        return ListLocalDataSource(executor, coroutineModule.coroutineIODispatcher(), movieDao)
    }

    @Provides
    @Singleton
    fun provideListRemoteDataSource(coroutineModule: CoroutineModule, movieApi: ApiServiceImpl): ListDataSource.Remote {
        return ListRemoteDataSource(coroutineModule.coroutineIODispatcher(), movieApi)
    }

    @Provides
    fun provideGetListUseCase(movieRepository: ListRepository): ListUseCase {
        return ListUseCase(movieRepository)
    }




    @Provides
    @Singleton
    fun provideGridRepository(remote: GridDataSource.Remote, local: GridDataSource.Local): GridRepository {
        return GridRepositoryImpl(local, remote)
    }

    @Provides
    @Singleton
    fun provideGridLocalDataSource(executor: DiskExecutor, module: CoroutineModule, dao: GridDao): GridDataSource.Local {
        return GridLocalDataSource(executor, module.coroutineIODispatcher(), dao)
    }

    @Provides
    @Singleton
    fun provideGridRemoteDataSource(module: CoroutineModule, api: ApiServiceImpl): GridDataSource.Remote {
        return GridRemoteDataSource(module.coroutineIODispatcher(), api)
    }

    @Provides
    fun provideGetGridUseCase(repository: GridRepository): GridUseCase {
        return GridUseCase(repository)
    }




    @Provides
    @Singleton
    fun providePagingRepository(remote: PagingDataSource.Remote, local: PagingDataSource.Local): PagingRepository {
        return PagingRepositoryImpl(local, remote)
    }

    @Provides
    @Singleton
    fun providePagingLocalDataSource(executor: DiskExecutor, module: CoroutineModule, dao: PagingDao): PagingDataSource.Local {
        return PagingLocalDataSource(executor, module.coroutineIODispatcher(), dao)
    }

    @Provides
    @Singleton
    fun providePagingRemoteDataSource(module: CoroutineModule, api: ApiServiceImpl): PagingDataSource.Remote {
        return PagingRemoteDataSource(module.coroutineIODispatcher(), api)
    }

    @Provides
    fun provideGetPagingUseCase(repository: PagingRepository): PagingUseCase {
        return PagingUseCase(repository)
    }




    @Provides
    @Singleton
    @Suppress("DEPRECATION")
    fun provideIsNetworkAvailable(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        return activeNetwork != null && activeNetwork.isConnected
    }
}