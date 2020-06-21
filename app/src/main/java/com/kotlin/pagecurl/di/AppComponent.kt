package com.kotlin.pagecurl.di

import com.kotlin.pagecurl.MyApplication
import com.kotlin.pagecurl.di.module.ActivityModule
import com.kotlin.pagecurl.di.module.NetworkModule
import com.kotlin.pagecurl.di.module.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        ActivityModule::class,
        ViewModelModule::class,
        NetworkModule::class
    ]
)
interface AppComponent : AndroidInjector<MyApplication> {
    override fun inject(application: MyApplication)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: MyApplication): Builder

        fun build(): AppComponent
    }
}
