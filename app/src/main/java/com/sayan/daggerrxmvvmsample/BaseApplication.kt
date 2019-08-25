package com.sayan.daggerrxmvvmsample

import com.sayan.daggerrxmvvmsample.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.support.DaggerApplication

class BaseApplication : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().application(this).build()
//        return null as AndroidInjector<out DaggerApplication>
    }
}