package com.sayan.daggerrxmvvmsample.ui

import android.os.Bundle
import android.widget.Toast
import com.sayan.daggerrxmvvmsample.R
import com.sayan.daggerrxmvvmsample.network.Service
import com.sayan.weatherapptest.models.CurrentWeather
import com.sayan.weatherapptest.util.OPEN_WEATHER_APP_ID
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var service: Service

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (::service.isInitialized){
            service.getCurrentWeatherInformation(OPEN_WEATHER_APP_ID, "London")
                .enqueue(
                    object : Callback<CurrentWeather>{
                        override fun onFailure(call: Call<CurrentWeather>, t: Throwable) {
                            Toast.makeText(
                                this@MainActivity,
                                "Failed",
                                Toast.LENGTH_SHORT
                            ).show()
                        }

                        override fun onResponse(
                            call: Call<CurrentWeather>,
                            response: Response<CurrentWeather>
                        ) {
                            text.text = "Name : ${response.body()?.name} Message : ${response.body()?.message}."
                        }

                    }
                )
        }
    }
}
