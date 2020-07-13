package com.acemirr.cleanarchitecture.di.core.component

import com.acemirr.cleanarchitecture.CleanApplication
import com.acemirr.cleanarchitecture.di.core.module.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjectionModule
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import javax.inject.Singleton

@Singleton
@Component(modules = [
    AndroidInjectionModule::class,
    AppModule::class,
    ActivityModule::class,
    CoroutineModule::class,
    DatabaseModule::class,
    DataModule::class
])
interface ApplicationComponent : AndroidInjector<DaggerApplication> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: CleanApplication): Builder

        fun build(): ApplicationComponent
    }

    override fun inject(instance: DaggerApplication)

    fun inject(application: CleanApplication)

}