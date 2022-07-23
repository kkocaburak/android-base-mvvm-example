package com.bkarakoca.exampleapp.internal.injection.module

import android.app.Application
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.bkarakoca.exampleapp.internal.util.ResourceProvider
import com.bkarakoca.exampleapp.internal.util.ResourceProviderImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
internal class AppModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(application: Application): SharedPreferences {
        return PreferenceManager.getDefaultSharedPreferences(
            application.applicationContext
        )
    }

    @Provides
    @Singleton
    fun provideResourceProvider(application: Application): ResourceProvider {
        return ResourceProviderImpl(application.applicationContext)
    }
}