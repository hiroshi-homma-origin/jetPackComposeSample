package com.kotlin.pagecurl.viewModel

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kotlin.pagecurl.MyApplication
import com.kotlin.pagecurl.data.api.ApolloController
import com.kotlin.pagecurl.data.repository.CurlViewRepository.Companion.pokemonLiveData
import com.kotlin.pagecurl.domainobject.model.pokeListSizeFirst
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SplashViewModel constructor(
    application: Application
) : AndroidViewModel(application) {

    fun getData() = pokemonLiveData

    fun fetchData() {
        viewModelScope.launch(Dispatchers.Default) {
            ApolloController.registerPokemonLiveData(pokeListSizeFirst)
//            ApolloController.registerPokemonLiveData(pokeListMaxSize)
        }
    }

    private fun Int.getContextString(): String {
        return getApplication<MyApplication>().applicationContext.getString(this)
    }

    private fun Int.getContextDrawable(): Drawable? {
        return getApplication<MyApplication>().applicationContext.getDrawable(this)
    }
}
