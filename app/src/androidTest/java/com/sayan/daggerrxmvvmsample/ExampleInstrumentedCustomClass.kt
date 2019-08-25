package com.sayan.daggerrxmvvmsample

import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented customClass, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedCustomClass {
    @Test
    fun useAppContext() {
        // Context of the app under customClass.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.sayan.daggerrxmvvmsample", appContext.packageName)
    }
}
