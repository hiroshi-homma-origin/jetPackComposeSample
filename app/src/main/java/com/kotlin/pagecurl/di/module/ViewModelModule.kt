package com.kotlin.pagecurl.di.module

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.kotlin.pagecurl.di.ViewModelFactory
import com.kotlin.pagecurl.di.ViewModelKey
import com.kotlin.pagecurl.presentation.curlViewer.CurlViewModel
import com.kotlin.pagecurl.presentation.home.HomeViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(CurlViewModel::class)
    abstract fun bindCurlViewModel(viewModel: CurlViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class)
    abstract fun bindHomeViewModel(viewModel: HomeViewModel): ViewModel
}
