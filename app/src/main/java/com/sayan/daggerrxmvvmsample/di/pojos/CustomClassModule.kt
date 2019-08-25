package com.sayan.daggerrxmvvmsample.di.pojos

import com.sayan.daggerrxmvvmsample.pojos.CustomClass
import com.sayan.daggerrxmvvmsample.pojos.DependantCustomClass
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class CustomClassModule {
    @Singleton
    @Provides
    internal fun provideTestClass(testString: String): CustomClass {
        val test = CustomClass()
        test.testString = testString
        return test
    }

    @Singleton
    @Provides
    internal fun provideDependantTestClass(customClass: CustomClass): DependantCustomClass {
        return DependantCustomClass(customClass)
    }
}