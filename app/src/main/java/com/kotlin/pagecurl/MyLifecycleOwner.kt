package com.kotlin.pagecurl

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry

class MyLifecycleOwner : LifecycleOwner {

    private val lifecycleRegistry = LifecycleRegistry(this)

    override fun getLifecycle() = lifecycleRegistry

    fun start() {
        // STARTEDの状態にしてLiveDataをActiveにする
        lifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    fun stop() {
        // CREATEDの状態にしてLiveDataをInactiveにする
        lifecycleRegistry.markState(Lifecycle.State.CREATED)
    }
}
