package com.sayan.daggerrxmvvmsample.di

import com.sayan.daggerrxmvvmsample.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuildersModule {

    @ContributesAndroidInjector
    internal abstract fun contributeMainActivity(): MainActivity
}