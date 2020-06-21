package com.kotlin.pagecurl

import com.kotlin.pagecurl.data.AppContainer
import com.kotlin.pagecurl.data.AppContainerImpl
import com.kotlin.pagecurl.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication
import timber.log.Timber
import timber.log.Timber.DebugTree

open class MyApplication : DaggerApplication() {

    lateinit var container: AppContainer

    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
    }

    override fun onCreate() {
        super.onCreate()
        container = AppContainerImpl(this)
        Timber.plant(DebugTree())
    }
}
