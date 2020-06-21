package com.kotlin.pagecurl.presentation

import com.apollographql.apollo.ApolloClient
import okhttp3.Authenticator
import okhttp3.OkHttpClient.Builder
import okhttp3.Request
import okhttp3.Response
import okhttp3.Route
import okhttp3.logging.HttpLoggingInterceptor
import java.io.IOException

// Must DI Imp...
object ApolloController {
    private const val BASE_URL = "https://api.github.com/graphql"
    fun setupApollo(): ApolloClient {
        val okHttpClient = Builder()
            .authenticator(object : Authenticator {
                @Throws(IOException::class)
                override fun authenticate(route: Route?, response: Response): Request? {
                    return response.request.newBuilder()
                        .addHeader("Authorization", "Bearer c643c13e87a03a6c35ff87bba9dbd5aa60fc3af4")
                        .build()
                }
            })
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .build()
        return ApolloClient.builder().serverUrl(BASE_URL).okHttpClient(okHttpClient)
            .build()
    }
}
