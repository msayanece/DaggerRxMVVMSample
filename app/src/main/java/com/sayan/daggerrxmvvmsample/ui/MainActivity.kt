package com.sayan.daggerrxmvvmsample.ui

import android.os.Bundle
import android.widget.Toast
import com.sayan.daggerrxmvvmsample.pojos.CustomClass
import com.sayan.daggerrxmvvmsample.R
import com.sayan.daggerrxmvvmsample.pojos.DependantCustomClass
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var dependantCustomClass: DependantCustomClass

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (::dependantCustomClass.isInitialized)
            Toast.makeText(
                this,
                "sampleText==> ${dependantCustomClass.getCustomClassObject().getSampleString()}",
                Toast.LENGTH_LONG).show()
    }
}
