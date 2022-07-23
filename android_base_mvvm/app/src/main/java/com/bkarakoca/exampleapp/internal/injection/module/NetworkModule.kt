package com.bkarakoca.exampleapp.internal.injection.module

import com.bkarakoca.exampleapp.internal.util.NetworkStateHolder
import com.bkarakoca.exampleapp.internal.util.ResourceProvider
import com.bkarakoca.exampleapp.internal.util.api.ErrorHandlingInterceptor
import com.google.gson.Gson
import dagger.Lazy
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
internal class NetworkModule {

    companion object {
        private const val CLIENT_TIME_OUT_SEC = 30L
    }

    @Provides
    @Singleton
    fun provideHttpLoggingInterceptor(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        loggingInterceptor: HttpLoggingInterceptor,
        resourceProvider: ResourceProvider
    ): OkHttpClient {
        val httpClient = OkHttpClient.Builder()
            .connectTimeout(CLIENT_TIME_OUT_SEC, TimeUnit.SECONDS)
            .readTimeout(CLIENT_TIME_OUT_SEC, TimeUnit.SECONDS)
            .addInterceptor(loggingInterceptor)
            .addInterceptor(ErrorHandlingInterceptor(NetworkStateHolder, resourceProvider))

        return httpClient.build()
    }

    @Provides
    @Singleton
    fun provideGson(): Gson {
        return Gson().newBuilder().create()
    }

    @Provides
    @Singleton
    fun provideRetrofit(client: Lazy<OkHttpClient>, gson: Gson): Retrofit {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create(gson))
            .callFactory { client.get().newCall(it) }
            .build()
    }

}
