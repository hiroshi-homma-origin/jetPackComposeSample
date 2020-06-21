package com.kotlin.pagecurl.di.module

import com.kotlin.pagecurl.data.api.ProjectService
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.ConnectionSpec
import okhttp3.ConnectionSpec.Builder
import okhttp3.OkHttpClient
import okhttp3.TlsVersion.SSL_3_0
import okhttp3.TlsVersion.TLS_1_0
import okhttp3.TlsVersion.TLS_1_1
import okhttp3.TlsVersion.TLS_1_2
import okhttp3.TlsVersion.TLS_1_3
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.converter.wire.WireConverterFactory
import java.util.Collections
import javax.inject.Singleton

@Module
class NetworkModule {

    @Provides
    @Singleton
    fun provideMoshi(): Moshi {
        return Moshi.Builder().add(KotlinJsonAdapterFactory()).build()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val spec = Builder(ConnectionSpec.MODERN_TLS)
            .tlsVersions(TLS_1_3, TLS_1_2, TLS_1_1, TLS_1_0, SSL_3_0)
            .build()
        return OkHttpClient.Builder()
            .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.HEADERS))
            .connectionSpecs(Collections.singletonList(spec))
            .build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://reqres.in/")
            .client(okHttpClient)
            .addConverterFactory(WireConverterFactory.create())
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
    }

    @Singleton
    @Provides
    fun provideCurlViewApi(retrofit: Retrofit): ProjectService = retrofit.create(ProjectService::class.java)
}
