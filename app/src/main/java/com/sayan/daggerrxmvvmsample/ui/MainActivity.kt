package com.sayan.daggerrxmvvmsample.ui

import android.os.Bundle
import android.widget.Toast
import com.sayan.daggerrxmvvmsample.R
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var sampleText: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (::sampleText.isInitialized)
            Toast.makeText(this, "sampleText==> $sampleText", Toast.LENGTH_LONG).show()
    }
}
