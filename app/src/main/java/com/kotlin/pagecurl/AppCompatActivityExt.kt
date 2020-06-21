package com.kotlin.pagecurl

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders

inline fun <reified T : ViewModel> AppCompatActivity.obtainViewModel(
    viewModelFactory: ViewModelProvider.Factory
) = ViewModelProviders.of(this, viewModelFactory).get(T::class.java)
