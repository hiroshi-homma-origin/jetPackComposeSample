package com.kotlin.pagecurl.viewModel

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.pagecurl.MyApplication
import com.kotlin.pagecurl.R
import com.kotlin.pagecurl.data.api.ApolloController
import com.kotlin.pagecurl.data.repository.CurlViewRepository.Companion.pokemonLiveData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel constructor(
    application: Application
) : AndroidViewModel(application) {

    fun fetchData() {
        Timber.d("check_getString:${R.string.app_name.getContextString()}")
        Timber.d("check_getDrawable:${R.drawable.placeholder.getContextDrawable()}")
        viewModelScope.launch(Dispatchers.Default) {
            ApolloController.registerPokemonLiveData()
        }
    }

    fun getData() = pokemonLiveData

    private fun Int.getContextString(): String {
        return getApplication<MyApplication>().applicationContext.getString(this)
    }

    private fun Int.getContextDrawable(): Drawable? {
        return getApplication<MyApplication>().applicationContext.getDrawable(this)
    }
}
