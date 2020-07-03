package com.kotlin.pagecurl.data.api

import com.apollographql.apollo.ApolloCall
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.api.cache.http.HttpCachePolicy
import com.apollographql.apollo.exception.ApolloException
import com.kotlin.pagecurl.api.PokemonsQuery
import com.kotlin.pagecurl.api.fragment.Pokemon
import com.kotlin.pagecurl.data.repository.CurlViewRepository
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor
import timber.log.Timber

// Must DI Imp...
object ApolloController {

    private const val BASE_URL = "https://graphql-pokemon.now.sh"

    private fun setupApollo(): ApolloClient {
        val okHttpClient = Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .build()
        return ApolloClient.builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .defaultHttpCachePolicy(HttpCachePolicy.CACHE_FIRST)
            .build()
    }

    fun registerPokemonLiveData(listSize: Int) {
        val pokemonList: MutableList<Pokemon> = mutableListOf()
        setupApollo()
            .query(PokemonsQuery.builder().first(listSize).build())
            .enqueue(object : ApolloCall.Callback<PokemonsQuery.Data>() {
                override fun onFailure(e: ApolloException) {
                    // Failure
                    Timber.d("check_testResponse_Failure:$e")
                }

                override fun onResponse(response: Response<PokemonsQuery.Data>) {
                    // Sucess
                    response.data?.pokemons()?.mapIndexed { index, pokemon ->
                        Timber.d("pokemonsList1:($index)${pokemon.fragments().pokemon().name()}")
                        pokemonList.add(pokemon.fragments().pokemon())
                    }
                    Timber.d("pokemonsList2:$pokemonList")
                    CurlViewRepository.pokemonLiveData.postValue(pokemonList)
                }
            })
    }
}
