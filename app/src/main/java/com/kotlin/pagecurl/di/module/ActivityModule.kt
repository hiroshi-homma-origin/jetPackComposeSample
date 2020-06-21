package com.kotlin.pagecurl.di.module

import com.kotlin.pagecurl.presentation.RootActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
internal abstract class ActivityModule {
    @ContributesAndroidInjector
    internal abstract fun contributeRootActivity(): RootActivity
}
