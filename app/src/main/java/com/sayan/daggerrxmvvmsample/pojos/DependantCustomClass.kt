package com.sayan.daggerrxmvvmsample.pojos

import javax.inject.Inject

class DependantCustomClass @Inject constructor(private val customClass: CustomClass) {
    fun getCustomClassObject(): CustomClass{
        return customClass
    }
}