package com.sayan.daggerrxmvvmsample.pojos

import javax.inject.Inject

class CustomClass {
    @Inject
    lateinit var testString: String

    fun getSampleString(): String{
        return testString
    }
}