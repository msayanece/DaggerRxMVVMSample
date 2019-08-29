package com.sayan.daggerrxmvvmsample.di

import com.sayan.daggerrxmvvmsample.network.Service
import com.sayan.weatherapptest.util.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {
    @Singleton
    @Provides
    internal fun provideRetrofitInstance(): Retrofit {
        return Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Singleton
    @Provides
    fun provideMainApi(retrofit: Retrofit): Service {
        return retrofit.create(Service::class.java)
    }

}