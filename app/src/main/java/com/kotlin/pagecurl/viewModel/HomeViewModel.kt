package com.kotlin.pagecurl.viewModel

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.pagecurl.MyApplication
import com.kotlin.pagecurl.data.api.ApolloController
import com.kotlin.pagecurl.data.repository.CurlViewRepository.Companion.pokemonLiveData
import com.kotlin.pagecurl.domainobject.model.pokeListSizeFirst
import com.kotlin.pagecurl.domainobject.state.CurlViewStatus.pokeListSize
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class HomeViewModel constructor(
    application: Application
) : AndroidViewModel(application) {
    fun fetchData() {
        viewModelScope.launch(Dispatchers.Default) {
            ApolloController.registerPokemonLiveData(pokeListSizeFirst)
        }
    }

    fun retry() {
        val listSize = pokeListSize
        viewModelScope.launch(Dispatchers.Default) {
            ApolloController.registerPokemonLiveData(listSize)
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
