package com.kotlin.pagecurl.presentation.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.exception.ApolloException
import com.kotlin.pagecurl.api.PokemonsQuery
import com.kotlin.pagecurl.api.PokemonsQuery.Data
import com.kotlin.pagecurl.api.fragment.Pokemon
import com.kotlin.pagecurl.data.ApolloController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel : ViewModel() {

    var pokemonList: MutableList<Pokemon> = mutableListOf()
    val pokemonLiveData: MutableLiveData<List<Pokemon>> = MutableLiveData()

    fun fetchData() {
        viewModelScope.launch(Dispatchers.Default) {
            ApolloController.setupApollo().query(PokemonsQuery.builder().first(151).build())
                .enqueue(object : ApolloCall.Callback<Data>() {
                    override fun onFailure(e: ApolloException) {
                        // Failure
                        Timber.d("check_testResponse_Failure:$e")
                    }

                    override fun onResponse(response: Response<Data>) {
                        // Sucess
                        response.data?.pokemons()?.mapIndexed { index, pokemon ->
                            Timber.d("pokemonsList1:($index)${pokemon.fragments().pokemon().name()}")
                            pokemonList.add(pokemon.fragments().pokemon())
                        }
                        Timber.d("pokemonsList2:$pokemonList")
                        pokemonLiveData.postValue(pokemonList)
                    }
                })
        }
    }
}
