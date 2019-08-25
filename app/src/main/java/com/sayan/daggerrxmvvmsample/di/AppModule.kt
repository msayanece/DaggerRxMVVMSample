package com.sayan.daggerrxmvvmsample.di

import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class AppModule {

    @Singleton
    @Provides
    internal fun provideSampleString(): String {
        return "xyzxxxx"
    }
}