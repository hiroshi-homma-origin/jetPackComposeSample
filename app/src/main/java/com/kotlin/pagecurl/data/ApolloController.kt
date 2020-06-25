package com.kotlin.pagecurl.data

import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.cache.http.HttpCachePolicy
import okhttp3.OkHttpClient.Builder
import okhttp3.logging.HttpLoggingInterceptor

// Must DI Imp...
object ApolloController {

    private const val BASE_URL = "https://graphql-pokemon.now.sh"

    fun setupApollo(): ApolloClient {
        val okHttpClient = Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .build()
        return ApolloClient.builder()
            .serverUrl(BASE_URL)
            .okHttpClient(okHttpClient)
            .defaultHttpCachePolicy(HttpCachePolicy.CACHE_FIRST)
            .build()
    }
}
