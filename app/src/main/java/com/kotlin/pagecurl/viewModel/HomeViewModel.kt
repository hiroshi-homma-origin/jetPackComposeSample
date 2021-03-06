package com.kotlin.pagecurl.viewModel

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import com.kotlin.pagecurl.MyApplication
import com.kotlin.pagecurl.data.repository.CurlViewRepository.Companion.pokemonLiveData

class HomeViewModel constructor(
    application: Application
) : AndroidViewModel(application) {

    fun getData() = pokemonLiveData

    private fun Int.getContextString(): String {
        return getApplication<MyApplication>().applicationContext.getString(this)
    }

    private fun Int.getContextDrawable(): Drawable? {
        return getApplication<MyApplication>().applicationContext.getDrawable(this)
    }
}
